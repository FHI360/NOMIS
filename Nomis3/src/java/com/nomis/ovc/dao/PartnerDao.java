/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.Partner;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface PartnerDao 
{
     public void savePartner(Partner p) throws Exception;
     public void updatePartner(Partner p) throws Exception;
     public void deletePartner(Partner p) throws Exception;
     public List getAllPartners() throws Exception;
     public List getPartnerList() throws Exception;
     public Partner getPartner(String partnercode) throws Exception;
}
