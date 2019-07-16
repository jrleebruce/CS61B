public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readDouble();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int planetNum = in.readInt();
        in.readDouble();
        Planet[] p = new Planet[planetNum];
        for(int i = 0; i < planetNum; i++) {
            p[i] = new Planet();
            p[i].xxPos = in.readDouble();
            p[i].yyPos = in.readDouble();
            p[i].xxVel = in.readDouble();
            p[i].yyVel = in.readDouble();
            p[i].mass = in.readDouble();
            p[i].imgFileName = in.readString();
        }
        return p;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        NBody.readRadius(filename);
        double uniRadius = NBody.readRadius(filename);
        StdDraw.setScale(-uniRadius, uniRadius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        Planet[] planets =  NBody.readPlanets(filename);
        for(int i = 0; i < planets.length; i++) {
            planets[i].draw();
        }

/*
        Planet[] planets =  NBody.readPlanets(filename);
        for (Planet planet : planets) {
            planet.draw();
        }
*/ 

        StdDraw.enableDoubleBuffering();
        double tVar = 0;
        while(tVar <= T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for(int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i = 0; i < planets.length; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            
            tVar = tVar + dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", uniRadius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }

        
    } 
}

