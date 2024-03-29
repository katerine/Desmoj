package desmoj.extensions.xml.report;

import java.io.File;

import org.w3c.dom.Document;

import desmoj.core.report.FileOutput;
import desmoj.core.report.OutputType;
import desmoj.extensions.xml.util.XMLHelper;

/**
 * Class to receive information from reporters and create an xml file out of it.
 * 
 * @version DESMO-J, Ver. 2.4.0 copyright (c) 2013
 * @author Gunnar Kiesel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */

public abstract class XMLOutput extends FileOutput implements OutputType {

	private int timeFloats = 0;

	private int reportNumber;

	/** A dummy document for XML output */
	protected Document document = XMLHelper.createDocument();

	/**
	 * Creates a filename for an XML output file.
	 * 
	 * @param pathname
	 *            name of the file path
	 * @param name
	 *            filename
	 * @param type
	 *            type of simulation output
	 * @return the complete filename as a string
	 */
	protected String createFileName(String pathname, String name, String type) {

		// check for proper path and filename
		if ((pathname == null) || (pathname.length() == 0))
			pathname = System.getProperty("user.dir", ".");
		if ((name == null) || (name != null && name.length() == 0))
			name = "DESMOJ";

		// check for proper suffix of the filename
		if ((!name.endsWith(".xml")) || (!name.endsWith("xml".toUpperCase())))
			return pathname + File.separator + name + "_" + type + ".xml";
		else
			return pathname + File.separator + name;
	}

	/** Call the super method to close the file and flush the buffers* */
 	public void close() {
		XMLHelper.serializeDocument(document, file); //%TODO Commented out by Diehl
		super.close();
	}

	/**
	 * set the time of the current output
	 * 
	 * @param timeFLoats
	 *            int: time of the curent output
	 */
	public void setTimeFloats(int timeFloats) {
		this.timeFloats = timeFloats;
	}

	/** returns the file appendix for this output type* */
	public String getAppendix() {
		return ".xml";
	}

}