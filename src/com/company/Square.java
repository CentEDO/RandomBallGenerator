package com.company;

import java.awt.*;

public class Square extends Ball{
    public Square() {
    }

    public Square(float x, float y, float radius, float speed, float angleInDegree, Color color) {
        super(x, y, radius, speed, angleInDegree, color);
    }

    public Square(float x, float y, float radius, float speed, float angleInDegree) {
        super(x, y, radius, speed, angleInDegree);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect((int)(x - radius), (int)(y - radius), (int)(4* radius), (int)(4* radius));
    }
}
