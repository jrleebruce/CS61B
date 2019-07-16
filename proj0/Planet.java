class Planet {
static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public Planet(){
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow((p.xxPos - this.xxPos), 2) + Math.pow((p.yyPos - this.yyPos), 2));
    }

    public double calcForceExertedBy(Planet p) {
        return (G * this.mass * p.mass) / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        return (this.calcForceExertedBy(p) * (p.xxPos - this.xxPos)) / this.calcDistance(p);
        /** Notice the sign of force */
    }

    public double calcForceExertedByY(Planet p) {
        return (this.calcForceExertedBy(p) * (p.yyPos - this.yyPos)) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for(Planet p : allPlanets) {
            if (!this.equals(p)) {
            netForceX = netForceX + this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for(Planet p : allPlanets) {
            if (!this.equals(p)) {
            netForceY = netForceY + this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        this.xxVel = this.xxVel + (fX / this.mass) * dt;
        this.yyVel = this.yyVel + (fY / this.mass) * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}