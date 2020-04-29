package com.zhh.train.authorization.jpa;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class CollectionToStringUserType implements UserType, ParameterizedType, Serializable {
    private String separator;
    private Class elementType;
    private Class collectionType;

    public CollectionToStringUserType() {
    }

    @Override
    public void setParameterValues(Properties parameters) {
        String separator = (String) parameters.get("separator");
        if (!StringUtils.isEmpty(separator)) {
            this.separator = separator;
        } else {
            this.separator = ",";
        }

        String collectionType = (String) parameters.get("collectionType");
        if (!StringUtils.isEmpty(collectionType)) {
            try {
                this.collectionType = Class.forName(collectionType);
            } catch (ClassNotFoundException var7) {
                throw new HibernateException(var7);
            }
        } else {
            this.collectionType = ArrayList.class;
        }

        String elementType = (String) parameters.get("elementType");
        if (!StringUtils.isEmpty(elementType)) {
            try {
                this.elementType = Class.forName(elementType);
            } catch (ClassNotFoundException var6) {
                throw new HibernateException(var6);
            }
        } else {
            this.elementType = String.class;
        }

    }

    @Override
    public int[] sqlTypes() {
        return new int[]{12};
    }

    @Override
    public Class returnedClass() {
        return this.collectionType;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o == o1) {
            return true;
        } else {
            return o == null ? false : o.equals(o1);
        }
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        String valueStr = rs.getString(names[0]);
        if (StringUtils.isEmpty(valueStr)) {
            return this.newCollection();
        } else {
            String[] values = StringUtils.split(valueStr, this.separator);
            Collection result = this.newCollection();
            String[] var8 = values;
            int var9 = values.length;

            for (int var10 = 0; var10 < var9; ++var10) {
                String value = var8[var10];
                if (StringUtils.isNotEmpty(value)) {
                    result.add(ConvertUtils.convert(value, this.elementType));
                }
            }

            return result;
        }
    }

    private Collection newCollection() {
        try {
            return (Collection) this.collectionType.newInstance();
        } catch (Exception var2) {
            throw new HibernateException(var2);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        String valueStr;
        if (value == null) {
            valueStr = "";
        } else {
            valueStr = StringUtils.join((Collection) value, this.separator);
        }

        /*if (StringUtils.isNotEmpty(valueStr)) {
            valueStr = valueStr + ",";
        }*/

        st.setString(index, valueStr);
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        if (o == null) {
            return null;
        } else {
            Collection copyCollection = this.newCollection();
            copyCollection.addAll((Collection) o);
            return copyCollection;
        }
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
