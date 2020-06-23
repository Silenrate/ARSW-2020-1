/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filters.PointsFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("blueprintServices")
public class BlueprintsServices {
    @Autowired
    @Qualifier("inMemoryBlueprint")
    BlueprintsPersistence bpp;

    @Autowired
    @Qualifier("blueprintFilter")
    PointsFilter pf;

    public Blueprint filter(Blueprint blueprint){
        return pf.filter(blueprint);
    }

    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        try {
            bpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException e) {
            throw new BlueprintPersistenceException("Error al guardar plano",e);
        }
    }
    
    public Set<Blueprint> getAllBlueprints(){
        return bpp.getAllBlueprints();
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        Blueprint blueprint;
        blueprint = bpp.getBlueprint(author,name);
        return blueprint;
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        HashSet<Blueprint> blueprints;
        blueprints = bpp.getBlueprintsByAuthor(author);
        return blueprints;
    }

    public BlueprintsPersistence getBpp() {
        return bpp;
    }

    public void setBpp(BlueprintsPersistence bpp) {
        this.bpp = bpp;
    }

    public void updateBlueprint(Blueprint blueprint) throws BlueprintNotFoundException {
        bpp.updateBlueprint(blueprint);
    }
    
}
