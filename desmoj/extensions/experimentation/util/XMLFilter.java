package desmoj.extensions.experimentation.util;

/**
 * A simple FileFilter for .xml files
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

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XMLFilter extends FileFilter {

	/**
	 * Accepts all directories and xml files.
	 * 
	 * @param file
	 *            file: file to be filtered
	 */
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		String ext = null;
		String s = file.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		if (ext != null) {
			if (ext.equals("xml")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/** @return The description of this filter */
	public String getDescription() {
		return "*.xml";
	}
}