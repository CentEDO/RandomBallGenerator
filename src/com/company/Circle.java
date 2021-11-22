package com.company;

import java.awt.*;

public class Circle extends Ball{
    public Circle() {
    }

    public Circle(float x, float y, float radius, float speed, float angleInDegree, Color color) {
        super(x, y, radius, speed, angleInDegree, color);
    }

    public Circle(float x, float y, float radius, float speed, float angleInDegree) {
        super(x, y, radius, speed, angleInDegree);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval((int)(x - radius), (int)(y - radius), (int)(4* radius), (int)(4* radius));
    }

}
