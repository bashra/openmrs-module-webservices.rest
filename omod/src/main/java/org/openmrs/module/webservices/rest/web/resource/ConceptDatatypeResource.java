/**
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
package org.openmrs.module.webservices.rest.web.resource;

import java.util.List;

import org.openmrs.ConceptDatatype;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.MetadataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

/**
 * {@link Resource} for {@link ConceptDatatype}, supporting standard CRUD operations
 */
@Resource("conceptdatatype")
@Handler(supports = ConceptDatatype.class, order = 0)
public class ConceptDatatypeResource extends MetadataDelegatingCrudResource<ConceptDatatype> {
	
	/**
	 * @see DelegatingCrudResource#getRepresentationDescription(Representation)
	 */
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		if (rep instanceof DefaultRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("description");
			return description;
		} else if (rep instanceof FullRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("description");
			description.addProperty("hl7Abbreviation");
			return description;
		}
		return null;
	}
	
	/**
	 * @see DelegatingCrudResource#newDelegate()
	 */
	@Override
	public ConceptDatatype newDelegate() {
		return new ConceptDatatype();
	}
	
	/**
	 * @see DelegatingCrudResource#save(java.lang.Object)
	 */
	@Override
	public ConceptDatatype save(ConceptDatatype conceptDatatype) {
		return Context.getConceptService().saveConceptDatatype(conceptDatatype);
	}
	
	/**
	 * @see DelegatingCrudResource#getByUniqueId(java.lang.String)
	 */
	@Override
	public ConceptDatatype getByUniqueId(String uuid) {
		return Context.getConceptService().getConceptDatatypeByUuid(uuid);
	}
	
	/**
	 * @see DelegatingCrudResource#delete(java.lang.Object, String, RequestContext)
	 */
	@Override
	public void delete(ConceptDatatype conceptDatatype, String reason, RequestContext context) throws ResponseException {
		//This is useless for now since the API has no retireConceptDatatype(Datatype, reason) method
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource#purge(java.lang.Object,
	 *      org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@Override
	public void purge(ConceptDatatype conceptDatatype, RequestContext context) throws ResponseException {
		if (conceptDatatype == null)
			return;
		Context.getConceptService().purgeConceptDatatype(conceptDatatype);
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource#doGetAll(org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@Override
	protected List<ConceptDatatype> doGetAll(RequestContext context) {
		return Context.getConceptService().getAllConceptDatatypes();
	}
	
}