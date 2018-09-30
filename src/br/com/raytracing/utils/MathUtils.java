package br.com.raytracing.utils;

public class MathUtils {

	public static double det(double[][] m) {
		double det =0;
		det = m[0][0] * m[1][1] * m[2][2];
        det += m[0][1] * m[1][2] * m [2][0];
        det += m[0][2] * m[1][0] * m[2][1];
        det += -1 * m[0][2] * m[1][1] * m[2][0];
        det += -1 * m[0][1] * m[1][0] * m[2][2];
        det += -1 * m[0][0] * m[1][2] * m[2][1];		
		return det;
	}
}
