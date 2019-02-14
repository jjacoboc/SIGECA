/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.common.persistence;

import java.util.ArrayList;

/**
 *
 * @author jjacobo
 */
public interface DataAccess {
    public void insert(Object obj) throws DAOException;
    public void update(Object obj) throws DAOException;
    public void delete(Object obj) throws DAOException;
    public Object findByPrimaryKey(Object obj) throws DAOException;
    public Object findByName(Object obj) throws DAOException;
    public boolean hasPresentReferences(Object obj) throws DAOException;
    public ArrayList list() throws DAOException;

}
