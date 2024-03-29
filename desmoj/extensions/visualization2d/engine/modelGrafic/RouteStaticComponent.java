package desmoj.extensions.visualization2d.engine.modelGrafic;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

import desmoj.extensions.visualization2d.engine.model.Model;
import desmoj.extensions.visualization2d.engine.model.Route;

/**
 * 
 * @version DESMO-J, Ver. 2.4.0 copyright (c) 2013
 * @author christian.mueller@th-wildau.de
 *         For information about subproject: desmoj.extensions.visualization2d
 *         please have a look at: 
 *         http://www.th-wildau.de/cmueller/Desmo-J/Visualization2d/ 
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License"); you
 *         may not use this file except in compliance with the License. You may
 *         obtain a copy of the License at
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *         implied. See the License for the specific language governing
 *         permissions and limitations under the License.
 *
 */
public class RouteStaticComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private String 	viewId;
	private Model	model;

	public RouteStaticComponent(Model model, String viewId, Rectangle bound){
		this.viewId		= viewId;
		this.model		= model;
		this.setLayout(null);
		this.setBounds(bound);
		this.setOpaque(false);
		this.setDoubleBuffered(true);
	}
	
	public void paintComponent(Graphics g){
		String[] id	= model.getRoutes().getAllIds();
		for(int i=0; i<id.length; i++){
			Route route = model.getRoutes().get(id[i]);
			RouteGrafic routeGrafic = (RouteGrafic)route.getGrafic();
			if(routeGrafic != null && routeGrafic.getViewId().equals(this.viewId)){
				routeGrafic.getRouteStaticGrafic().paintComponent(g);
			}
		}

	}
}
