package com.company;

import java.awt.*;

public class Ellipse extends Ball{
    public Ellipse() {
    }

    public Ellipse(float x, float y, float radius, float speed, float angleInDegree, Color color) {
        super(x, y, radius, speed, angleInDegree, color);
    }

    public Ellipse(float x, float y, float radius, float speed, float angleInDegree) {
        super(x, y, radius, speed, angleInDegree);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval((int)(x - radius), (int)(y - radius), (int)(8* radius), (int)(2* radius));
    }
}
