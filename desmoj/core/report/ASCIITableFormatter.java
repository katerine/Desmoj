package desmoj.core.report;

import desmoj.core.simulator.Experiment;

/**
 * A table formatter class for writing simulation output to tab delimited ASCII
 * (e.g. for import into statistics tools or spreadsheet software).
 * 
 * @version DESMO-J, Ver. 2.4.0 copyright (c) 2013
 * @author Nicolas Knaak
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
public class ASCIITableFormatter extends AbstractTableFormatter {

	/**
	 * Writes a newline character
	 * 
	 * @see desmoj.core.report.TableFormatter#closeRow()
	 */
	public void closeRow() {
		rowOpen = false;
		out.writeln("");
		this._currentReporter = null;
	}

	/**
	 * Writes 2 newline characters
	 * 
	 * @see desmoj.core.report.TableFormatter#closeTable()
	 */
	public void closeTable() {
		tableOpen = false;
		out.writeln("");
	}

	/**
	 * Calls closeTable
	 * 
	 * @see desmoj.core.report.TableFormatter#closeTableNoTopTag()
	 */
	public void closeTableNoTopTag() {
		closeTable();
	}

	/**
	 * Only sets rowOpen flag to true
	 * 
	 * @see desmoj.core.report.TableFormatter#openRow()
	 */
	public void openRow() {
		rowOpen = true;
	}

	/**
	 * Opens the table by writing the heading and a newline character
	 * 
	 * @param heading
	 *            heading to write
	 * @see desmoj.core.report.TableFormatter#openTable(java.lang.String)
	 */
	public void openTable(String heading) {
		tableOpen = true;
		out.writeln(heading + FileOutput.getEndOfLine());
	}

	/**
	 * Writes the given string followed by a separator character
	 * 
	 * @param s
	 *            string to write
     * @param spanning
     *            number of cells to span (ignored)
	 * @see desmoj.core.report.TableFormatter#writeCell(java.lang.String)
	 */
	public void writeCell(String s, int spanning) {
		out.writeSep(s);
	}

	/**
	 * Writes the given heading enclosed in asterisks followed by a newline
	 * character. The integer parameter's value has no effect on ASCII tables.
	 * 
	 * @param i
	 *            heading size (without effect)
	 * @param s
	 *            heading to write
	 * @see desmoj.core.report.TableFormatter#writeHeading(int,
	 *      java.lang.String)
	 */
	public void writeHeading(int i, String s) {
		out.writeln("** " + s + " **");
		out.writeln("");
	}

	/**
	 * Writes a heading cell. Heading cells are not formatted in a special way
	 * in ASCII tables.
	 * 
	 * @param s
	 *            heading to write
	 * @see desmoj.core.report.TableFormatter#writeHeadingCell(java.lang.String)
	 */
	public void writeHeadingCell(String s) {
		out.writeSep(s);
	}

	/**
	 * Writes a line of asterisks and a newline.
	 * 
	 * @see desmoj.core.report.TableFormatter#writeHorizontalRuler()
	 */
	public void writeHorizontalRuler() {
		out.writeln(FileOutput.getEndOfLine() + "********************************");
		out.writeln("");
	}

	/**
	 * Opens the table (without effect for ASCII tables)
	 * 
	 * @param name
	 *            table name (not displayed)
	 */
	public void open(String name) {
	}

	/**
	 * Closes the document by closing open rows and tables and writing the
	 * Desmo-J version footer
	 */
	public void close() {
		if (rowOpen)
			closeRow();
		if (tableOpen)
			closeTable();
		out.write("Created using DESMO-J Version " + Experiment.getDesmoJVersion()
				+ " at " + new java.util.Date()
				+ " - DESMO-J is licensed under " + Experiment.getDesmoJLicense(false));
	}

	/** @return <code>"txt"</code> */
	public String getFileFormat() {
		return "txt";
	}
}