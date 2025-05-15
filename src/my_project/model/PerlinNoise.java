package my_project.model;

import java.util.Random;

public class PerlinNoise {

    private int[] perm;
    private double baseScale;
    private int octaves;
    private double persistence;
    private double lacunarity;

    public PerlinNoise(long seed, double baseScale, int octaves, double persistence, double lacunarity) {
        this.baseScale = baseScale;
        this.octaves = octaves;
        this.persistence = persistence;
        this.lacunarity = lacunarity;

        perm = new int[512];
        int[] p = new int[256];

        for (int i = 0; i < 256; i++) p[i] = i;

        Random rand = new Random(seed);
        for (int i = 255; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = p[i];
            p[i] = p[j];
            p[j] = temp;
        }

        for (int i = 0; i < 512; i++) {
            perm[i] = p[i % 256];
        }
    }

    // ==== 2D FRACTAL NOISE ====
    public double getValue(double x, double y) {
        double total = 0;
        double frequency = baseScale;
        double amplitude = 1;
        double maxAmplitude = 0;

        for (int i = 0; i < octaves; i++) {
            total += singleNoise2D(x * frequency, y * frequency) * amplitude;
            maxAmplitude += amplitude;

            amplitude *= persistence;
            frequency *= lacunarity;
        }

        return total / maxAmplitude;
    }

    // ==== 1D FRACTAL NOISE ====
    public double getValue(double x) {
        double total = 0;
        double frequency = baseScale;
        double amplitude = 1;
        double maxAmplitude = 0;

        for (int i = 0; i < octaves; i++) {
            total += singleNoise1D(x * frequency) * amplitude;
            maxAmplitude += amplitude;

            amplitude *= persistence;
            frequency *= lacunarity;
        }

        return total / maxAmplitude;
    }

    // === Core 2D Perlin noise ===
    private double singleNoise2D(double x, double y) {
        int xi = (int)Math.floor(x) & 255;
        int yi = (int)Math.floor(y) & 255;

        double xf = x - Math.floor(x);
        double yf = y - Math.floor(y);

        double u = fade(xf);
        double v = fade(yf);

        int aa = perm[perm[xi] + yi];
        int ab = perm[perm[xi] + yi + 1];
        int ba = perm[perm[xi + 1] + yi];
        int bb = perm[perm[xi + 1] + yi + 1];

        double x1 = lerp(u, grad2D(aa, xf, yf), grad2D(ba, xf - 1, yf));
        double x2 = lerp(u, grad2D(ab, xf, yf - 1), grad2D(bb, xf - 1, yf - 1));
        return lerp(v, x1, x2);
    }

    // === Core 1D Perlin noise ===
    private double singleNoise1D(double x) {
        int xi = (int)Math.floor(x) & 255;
        double xf = x - Math.floor(x);

        double u = fade(xf);

        int a = perm[xi];
        int b = perm[xi + 1];

        return lerp(u, grad1D(a, xf), grad1D(b, xf - 1));
    }

    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private double grad2D(int hash, double x, double y) {
        int h = hash & 7;
        double u = h < 4 ? x : y;
        double v = h < 4 ? y : x;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    private double grad1D(int hash, double x) {
        return ((hash & 1) == 0) ? x : -x;
    }
}
