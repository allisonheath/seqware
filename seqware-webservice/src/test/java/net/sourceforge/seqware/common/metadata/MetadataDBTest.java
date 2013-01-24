/*
 * Copyright (C) 2012 SeqWare
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.seqware.common.metadata;

import java.sql.Timestamp;
import java.util.Date;
import junit.framework.Assert;
import net.sourceforge.seqware.common.factory.DBAccess;
import net.sourceforge.seqware.common.module.ReturnValue;
import org.apache.log4j.Logger;
import org.junit.*;

/**
 *
 * @author mtaschuk
 */
public class MetadataDBTest extends MetadataWSTest {

    private Logger logger;

    public MetadataDBTest() {
        logger = Logger.getLogger(MetadataDBTest.class);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Before
    public void setUp() {
        //This method needs to be called before every method because the methods 
        //inside might close the statement after finishing
        instance = DBAccess.get();
    }
    
    @Test
    public void testUpdate_workflow_run() {
        logger.info("update_workflow_run");
        Date beforeDate = new Timestamp(System.currentTimeMillis());
        int workflowRunId = 32;
        String pegasusCmd = "test pegasus cmd";
        String workflowTemplate = "test template";
        String status = "test status ";
        String statusCmd = "test command";
        String workingDirectory = "test working dir";
        String dax = "test dax";
        String ini = "test ini";
        String host = "localhost";
        String workflowEngine = "test engine";
        int expResult = 0;
        ReturnValue result = instance.update_workflow_run(workflowRunId, pegasusCmd, workflowTemplate, status, statusCmd, workingDirectory, dax, ini, host, 0, 0, null, null, workflowEngine);
        Assert.assertEquals(expResult, result.getReturnValue());
        testTimestamp("select update_tstmp from workflow_run "
                + "where workflow_run_id=32;", "update_tstmp", beforeDate);
    }

    @Test
    public void testFindFilesAssociatedWithASample() {
    }

    /**
     * Test of findSamplesAssociatedWithAStudy method, of class MetadataWS.
     */
    @Test
    public void testFindSamplesAssociatedWithAStudy() {
    }

    @Override
    public void testGetFile() {
    }

    @Override
    public void testGetAllSequencerRuns() {
        
    }

    @Override
    public void testGetChildSamplesFrom() {
        
    }

    @Override
    public void testGetExperimentsFrom() {
        
    }

    @Override
    public void testGetIUSFromLane() {
    }

    @Override
    public void testGetIUSFromSample() {
    }

    @Override
    public void testGetLanesFrom() {
    }

    @Override
    public void testGetParentSamplesFrom() {
    }

    @Override
    public void testGetSamplesFromExperiment() {
        
    }
    
    
    
    
    
}
