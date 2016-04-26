package fr.dauphine.lamsade.hib.biblio.service.impl;

import fr.dauphine.lamsade.hib.biblio.modele.Evaluation;
import fr.dauphine.lamsade.hib.biblio.service.inter.EvaluationServiceRemote;


/**
 * 
 * @author Bouhirmani Mohammed
 *
 * @date 31 mars 2016
 */
public class EvaluationServiceImpl extends ServiceImpl<Evaluation> 
		implements EvaluationServiceRemote{
	
	public EvaluationServiceImpl() {
		super(Evaluation.class);
	}

}
