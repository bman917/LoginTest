/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan.logintest;

import com.jchan.jtableutils.JDataModel;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Mr Jacky
 */
public class CredentialDataModel extends JDataModel<Credential> {

    static final String USERNAME = "userName";
    static final String PASSWORD = "password";

    public CredentialDataModel() {
        super(Credential.class);
    }

    @Override
    public String getFeild(Credential genericType, int index) {

        String columName = getColumnName(index);

        try
        {
            return BeanUtils.getProperty(genericType, columName);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setField(Object value, Credential genericType, int index) {
    }

    @Override
    public void addBlankRow() {
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
