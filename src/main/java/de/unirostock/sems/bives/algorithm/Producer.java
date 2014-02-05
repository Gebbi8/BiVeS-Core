/**
 * 
 */
package de.unirostock.sems.bives.algorithm;

import de.unirostock.sems.xmlutils.ds.TreeDocument;


/**
 * @author Martin Scharm
 *
 */
public abstract class Producer
{
	protected ClearConnectionManager conMgmt;
	protected TreeDocument docA;
	protected TreeDocument docB;
	
	public Producer ()
	{
	}
	
	public void init (ClearConnectionManager conMgmt, TreeDocument docA, TreeDocument docB)
	{
		this.conMgmt = conMgmt;
		this.docA = docA;
		this.docB = docB;
	}
	
	public abstract String produce ();
}
