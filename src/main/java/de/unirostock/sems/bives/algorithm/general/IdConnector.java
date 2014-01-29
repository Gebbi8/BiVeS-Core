/**
 * 
 */
package de.unirostock.sems.bives.algorithm.general;

import java.util.Set;

import de.unirostock.sems.bives.algorithm.Connector;
import de.unirostock.sems.bives.algorithm.NodeConnection;
import de.unirostock.sems.bives.exception.BivesConnectionException;
import de.unirostock.sems.xmltools.ds.DocumentNode;
import de.unirostock.sems.xmltools.ds.TreeNode;


/**
 * Connector to connect nodes with same id.
 * 
 * @author Martin Scharm
 *
 */
public class IdConnector
	extends Connector
{
	
	/* (non-Javadoc)
	 * @see de.unirostock.sems.xmldiff.algorithm.Connector#findConnections()
	 */
	@Override
	protected void connect () throws BivesConnectionException
	{
		findConnections (true);
	}
	
	/**
	 * Find connections.
	 *
	 * @param requireSameLabel if true, both id-tags need to have the same label
	 * @throws BivesConnectionException 
	 */
	public void findConnections (boolean requireSameLabel) throws BivesConnectionException
	{
		// we can only map by ids if they are unique...
		if (!docA.uniqueIds () || !docB.uniqueIds ())
			return;
		
		Set<String> ids = docA.getOccuringIds ();
		
		for (String id : ids)
		{
			TreeNode nB = docB.getNodeById (id);
			if (nB == null)
				continue;
			
			TreeNode nA = docA.getNodeById (id);

			if (!requireSameLabel)
			{
				conMgmt.addConnection (new NodeConnection (nA, nB));
			}
			else if (nB.getType () == TreeNode.DOC_NODE && nA.getType () == TreeNode.DOC_NODE && ((DocumentNode) nB).getTagName ().equals (((DocumentNode) nA).getTagName ()))
			{
				conMgmt.addConnection (new NodeConnection (nA, nB));
			}
		}
	}
	
}
