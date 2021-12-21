/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.validator;

import com.hlk.model.Medicine;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author MSIGE66
 */
@Component
public class MedicineValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Medicine.class.isAssignableFrom(Medicine.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Medicine m = (Medicine) target;
        if(!m.getName().contains("thuoc")){
            errors.rejectValue("name", "medicine.nameNotContains");
        
        }
        if(m.getExpiryDate().before(new Date())){
            errors.rejectValue("expiryDate", "medicine.outExpiryDate");
        }
        if(m.getManufacturingDate().after(new Date())){
            errors.rejectValue("manufacturingDate", "medicine.outManuDate");
        }
    }
    
}
