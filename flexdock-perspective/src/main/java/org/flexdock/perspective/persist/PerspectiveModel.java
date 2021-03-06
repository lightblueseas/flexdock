/*
 * Copyright (c) 2005 FlexDock Development Team. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE.
 */
package org.flexdock.perspective.persist;

import java.io.Serializable;

import org.flexdock.perspective.Perspective;

/**
 * Created on 2005-03-30
 *
 * @author <a href="mailto:marius@eleritec.net">Christopher Butler</a>
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 * @version $Id: PerspectiveModel.java,v 1.1 2005-06-11 16:14:48 marius Exp $
 */
public class PerspectiveModel implements Serializable {

    private String defaultPerspective;
    private String currentPerspective;
    private Perspective[] perspectives;

    public PerspectiveModel(String defaultId, String current, Perspective[] perspectives) {
        this.defaultPerspective = defaultId;
        this.currentPerspective = current;
        this.perspectives = perspectives;
    }

    public String getDefaultPerspective() {
        return this.defaultPerspective;
    }

    public void setDefaultPerspective(String defaultPerspective) {
        this.defaultPerspective = defaultPerspective;
    }

    public Perspective[] getPerspectives() {
        return this.perspectives;
    }

    public void setPerspectives(Perspective[] perspectives) {
        this.perspectives = perspectives;
    }

    public String getCurrentPerspective() {
        return this.currentPerspective;
    }

    public void setCurrentPerspective(String currentPerspective) {
        this.currentPerspective = currentPerspective;
    }

}
