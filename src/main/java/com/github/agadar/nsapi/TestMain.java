package com.github.agadar.nsapi;

import com.github.agadar.nsapi.enums.shard.NationShard;

/**
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public class TestMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        NSAPI.setUserAgent("Agadar's Java Wrapper <testing>");
        NSAPI.registerJaxbTypes(NationTest.class);
        NationTest n = NSAPI.nation("agadar").shards(NationShard.FullName).execute(
                NationTest.class);
        String s = n.fancy();
        System.out.println(s);
        
        
//        try 
//        {
//            JAXBContext jc = JAXBContext.newInstance(DailyDumpNations.class, 
//                DailyDumpRegions.class, World.class, WorldAssembly.class);
//            
//            Unmarshaller unmarshaller = jc.createUnmarshaller();
//            StreamSource xmlstream = new StreamSource("C:\\Users\\Martin\\Desktop\\regions.xml");
//            JAXBElement je1 = unmarshaller.unmarshal(xmlstream, DailyDumpRegionsChild.class);
//            DailyDumpRegionsChild regions = (DailyDumpRegionsChild) je1.getValue();
//            System.out.println(regions.Regions.size());
//            System.out.println(regions.atIndex(5).Name);
//        } 
//        catch (JAXBException ex)
//        {
//            throw new NationStatesAPIException("Failed to parse XML!", ex);
//        }
    }
    
}
