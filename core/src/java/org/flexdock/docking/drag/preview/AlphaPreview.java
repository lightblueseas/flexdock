/* Copyright (c) 2004 Ismail Degani, Christopher M Butler
Permission is hereby granted, free of charge, to any person obtaining a copy of 
this software and associated documentation files (the "Software"), to deal in the 
Software without restriction, including without limitation the rights to use, 
copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the 
Software, and to permit persons to whom the Software is furnished to do so, subject 
to the following conditions:

The above copyright notice and this permission notice shall be included in all 
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
*/
package org.flexdock.docking.drag.preview;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class AlphaPreview implements DragPreview {

	public void drawPreview(Graphics2D g, Rectangle rect) {
		float alpha = .5f;
		g.setColor(Color.black);
		g.draw3DRect(rect.x, rect.y, rect.width-1, rect.height-1, false);
		Composite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha); 
		g.setComposite(composite);
		g.setColor(Color.white);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
	}
}