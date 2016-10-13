/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roovitechologies.phpx.extensions.php.bundles.MathBundle;

import org.roovitechologies.phpx.extensions.Extension;
import org.roovitechologies.phpx.lib.Functions;
import org.roovitechologies.phpx.lib.Constants;
import org.roovitechologies.phpx.lib.NumberMemory;

/**
 *
 * @author AndreyPC
 */
public class MathBundle implements Extension {

    @Override
    public void Register() {
        Functions.set("acos", new ACos());
        Functions.set("asin", new ASin());
        Functions.set("atan", new ATan());
        Functions.set("abs", new Abs());
        Functions.set("cos", new Cos());
        Functions.set("cosh", new Cosh());
        Functions.set("exp", new Exp());
        Functions.set("expm1", new Expm1());
        Functions.set("hypot", new Hypot());
        Functions.set("log", new Log());
        Functions.set("log1p", new Log1p());
        Functions.set("log10", new Log10());
        Functions.set("max", new Max());
        Functions.set("min", new Min());
        Functions.set("sin", new Sin());
        Functions.set("sinh", new Sinh());
        Functions.set("sqrt", new Sqrt());
        Functions.set("cbrt", new Cbrt());
        Functions.set("tan", new Tan());
        Functions.set("tanh", new Tanh());
        Constants.set("PI", NumberMemory.of(java.lang.Math.PI));
    }
    
}
