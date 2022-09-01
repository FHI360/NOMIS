/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.Partner;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author smomoh
 */
public class PartnerDaoImpl implements PartnerDao
{
    Session session;
    Transaction tx;
    List list;

    public List getAllPartners() throws Exception
    {
        List partnerList=new ArrayList();
        Partner partner=null;
        try
        {
        session=HibernateUtil.getSession();
        tx=session.beginTransaction();
        list=session.createQuery("from Partner").list();
        tx.commit();
        closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
        for(int i=0; i<list.size(); i++)
        {
            partner=(Partner)list.get(i);
            partnerList.add(partner);
        }
        return partnerList;
    }
    public Partner getPartner(String partnercode) throws Exception
    {
        Partner partner=null;
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=session.createQuery("from Partner partner where partner.partnerCode= :pcode").setString("pcode", partnercode).list();
            tx.commit();
            closeSession(session);
            if(list !=null && !list.isEmpty())
            {
                partner=(Partner)list.get(0);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
            ex.printStackTrace();
        }
        return partner;
    }
    public List getPartnerList() throws Exception
    {
        try
        {
            session=HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=session.createQuery("from Partner partner").list();
            tx.commit();
            closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
        return list;
    }
    public void savePartner(Partner partner) throws Exception
    {
        try
        {
        session=HibernateUtil.getSession();
        tx=session.beginTransaction();
        session.save(partner);
        tx.commit();
        closeSession(session);
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
    }
    public void updatePartner(Partner partner) throws Exception
    {
        try
        {
            if(partner !=null && getPartner(partner.getPartnerCode()) !=null)
            {
                session=HibernateUtil.getSession();
                tx=session.beginTransaction();
                session.update(partner);
                tx.commit();
                closeSession(session);
            }
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
    }
    public void deletePartner(Partner partner) throws Exception
    {
        try
        {
            /*if(partner !=null && getPartner(partner.getPartnerCode()) !=null)
            {
                session=HibernateUtil.getSession();
                tx=session.beginTransaction();
                session.delete(partner);
                tx.commit();
                closeSession(session);
            }*/
        }
        catch(Exception ex)
        {
            closeSession(session);
        }
    }
    public void closeSession(Session session)
    {
        if(session !=null && session.isOpen())
        {
            session.close();
        }
    }
}
