package com.omor.aws;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonParseException, IOException
    {
        System.out.println( "Hello World!" );
        try {
			InputStream is = new FileInputStream("/Users/mdomor.faruque/Projects/foundation-endeca-records.json");
			ObjectMapper mapper = new ObjectMapper();
			JsonParser parser = mapper.getFactory().createParser(is);
			int counter = 0;
			
			if (parser.nextToken() != JsonToken.START_ARRAY) {
				parser.close();
				throw new IllegalStateException("Expected an array");
			}
			// /Users/mdomor.faruque/Projects/foundation-endeca-records
			// /Users/mdomor.faruque/Tools/data/p/docs/accessories_removed_html.json
		
			
			while (parser.nextToken() == JsonToken.START_OBJECT) {
				// read everything from this START_OBJECT to the matching END_OBJECT
				// and return it as a tree model ObjectNode
				ObjectNode node = mapper.readTree(parser);
				System.out.println(node);
				counter++;
		
			}
			parser.close();
			
			
			
			
			
			System.out.println("Counter : "+counter);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");
			e.printStackTrace();
		}
        
        // /Users/mdomor.faruque/Tools/data/p/docs/accessories_removed_html.json
    }
}
