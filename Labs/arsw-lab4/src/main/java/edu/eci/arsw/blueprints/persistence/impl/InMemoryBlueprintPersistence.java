/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author hcadavid
 */
@Service("inMemoryBlueprint")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    //private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();
    private final ConcurrentMap<Tuple<String,String>,Blueprint> blueprints = new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        Blueprint bp2=new Blueprint("daniel", "obra");
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        Blueprint bp3=new Blueprint("carlos", "pintura",pts);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        Blueprint bp4=new Blueprint("carlos", "arbol");
        blueprints.put(new Tuple<>(bp4.getAuthor(),bp4.getName()), bp4);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint blueprint = blueprints.get(new Tuple<>(author, bprintname));
        if(blueprint==null){
            throw new BlueprintNotFoundException("El plano con el nombre "+bprintname+" no existe");
        }
        return blueprint;
    }

    @Override
    public HashSet<Blueprint> getAllBlueprints(){
        return new HashSet<Blueprint>(blueprints.values());
    }

    @Override
    public HashSet<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        HashSet<Blueprint> authorBlueprints = new HashSet<Blueprint>();
        for(Tuple<String,String> key : blueprints.keySet()){
            if(key.getElem1().equals(author)){
                authorBlueprints.add(blueprints.get(key));
            }
        }
        if(authorBlueprints.size()==0){
            throw new BlueprintNotFoundException("No existen planos cuyo autor es "+author);
        }
        return authorBlueprints;
    }

    @Override
    public void updateBlueprint(Blueprint bp) throws BlueprintNotFoundException {
        if (!blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintNotFoundException("No existe ese plano");
        }
        Blueprint blueprint = blueprints.get(new Tuple<>(bp.getAuthor(), bp.getName()));
        blueprint.setPoints(bp.getPoints());
    }


}
