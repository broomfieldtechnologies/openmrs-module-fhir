/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.fhir.resources;

import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.dstu3.model.FamilyMemberHistory;
import org.hl7.fhir.dstu3.model.IdType;
import org.openmrs.api.context.Context;
import org.openmrs.module.fhir.api.FamilyMemberHistoryService;

import java.util.List;

public class FHIRFamilyMemberHistoryResource extends Resource {

	public FamilyMemberHistory getByUniqueId(IdType id) {
		FamilyMemberHistoryService familyHistoryService = Context.getService(FamilyMemberHistoryService.class);
		FamilyMemberHistory familyHistory = familyHistoryService.getRelationshipById(id.getIdPart());
		if (familyHistory == null) {
			throw new ResourceNotFoundException("Family history is not found for the given Id " + id.getIdPart());
		}
		return familyHistory;
	}

	public List<FamilyMemberHistory> searchFamilyHistoryByUniqueId(TokenParam id) {
		FamilyMemberHistoryService familyHistoryService = Context.getService(FamilyMemberHistoryService.class);
		return familyHistoryService.searchRelationshipsById(id.getValue());
	}

	public List<FamilyMemberHistory> searchFamilyHistoryByPerson(ReferenceParam id) {
		FamilyMemberHistoryService familyHistoryService = Context.getService(FamilyMemberHistoryService.class);
		return familyHistoryService.searchFamilyHistoryByPersonId(id.getIdPart());
	}
}
