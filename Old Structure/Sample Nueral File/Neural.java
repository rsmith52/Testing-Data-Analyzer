import java.text.DecimalFormat;
import java.io.*;
import java.util.*;

public class Neural {

    static DecimalFormat df2 = new DecimalFormat("#.00000");
    static double[] weights = new double[9];

    static double sigmoid (double z) {
        return 1 / (double)(1 + Math.exp(-z));
    }

    static double computeOutput(double[] weights, double x1, double x2) {
        double ua = weights[0] * 1 + weights[1] * x1 + weights[2] * x2;
        double va = Math.max(ua, 0);
        double ub = weights[3] * 1 + weights[4] * x1 + weights[5] * x2;
        double vb = Math.max(ub, 0);
        double uc = weights[6] * 1 + weights[7] * va + weights[8] * vb;
        return sigmoid(uc);
    }

    public static void main (String[] args) {

        if (args.length < 1) {
            // No flag given
            System.out.println("Needs flag argument to be given at minimum. Usage: java Neural flag");
        } else if (args[0].equals("100")) {
            if (args.length != 12) {
                System.out.println("Need arguments for weights and x values.");
            } else {
                for (int i = 0; i < 9; i++) {
                    weights[i] = Double.parseDouble(args[i + 1]);
;               }
                double x1 = Double.parseDouble(args[10]);
                double x2 = Double.parseDouble(args[11]);
                double ua = weights[0] * 1 + weights[1] * x1 + weights[2] * x2;
                double va = Math.max(ua, 0);
                double ub = weights[3] * 1 + weights[4] * x1 + weights[5] * x2;
                double vb = Math.max(ub, 0);
                double uc = weights[6] * 1 + weights[7] * va + weights[8] * vb;
                double vc = sigmoid(uc);

                System.out.println(df2.format(ua) + " " + df2.format(va) + " " + df2.format(ub) + " " + df2.format(vb) + " " + df2.format(uc) + " " + df2.format(vc));
            }
        } else if (args[0].equals("200")) {
            if (args.length != 13) {
                System.out.println("Need areguments for weights, x values, and y value.");
            } else {
                for (int i = 0; i < 9; i++) {
                    weights[i] = Double.parseDouble(args[i + 1]);
                }
                double x1 = Double.parseDouble(args[10]);
                double x2 = Double.parseDouble(args[11]);
                double y = Double.parseDouble(args[12]);
                double vc = computeOutput(weights, x1, x2);
                double error = (1 / (double) 2) * Math.pow((vc - y), 2);
                double devc = vc - y;
                double deuc = devc * (vc * (1 - vc));

                System.out.println(df2.format(error) + " " + df2.format(devc) + " " + df2.format(deuc));
            }
        } else if (args[0].equals("300")) {
            if (args.length != 13) {
                System.out.println("Need areguments for weights, x values, and y value.");
            } else {
                for (int i = 0; i < 9; i++) {
                    weights[i] = Double.parseDouble(args[i + 1]);
                }
                double x1 = Double.parseDouble(args[10]);
                double x2 = Double.parseDouble(args[11]);
                double y = Double.parseDouble(args[12]);
                double ua = weights[0] * 1 + weights[1] * x1 + weights[2] * x2;
                double ub = weights[3] * 1 + weights[4] * x1 + weights[5] * x2;
                double vc = computeOutput(weights, x1, x2);
                double error = (1 / (double) 2) * Math.pow((vc - y), 2);
                double devc = vc - y;
                double deuc = devc * (vc * (1 - vc));
                double deva = weights[7] * deuc;
                double deua = deva;
                if (ua < 0) {
                    deua = 0;
                }
                double devb = weights[8] * deuc;
                double deub = devb;
                if (ub < 0) {
                    deub = 0;
                }

                System.out.println(df2.format(deva) + " " + df2.format(deua) + " " + df2.format(devb) + " " + df2.format(deub));
            }
        } else if (args[0].equals("400")) {
            if (args.length != 13) {
                System.out.println("Need areguments for weights, x values, and y value.");
            } else {
                for (int i = 0; i < 9; i++) {
                    weights[i] = Double.parseDouble(args[i + 1]);
                }
                double x1 = Double.parseDouble(args[10]);
                double x2 = Double.parseDouble(args[11]);
                double y = Double.parseDouble(args[12]);
                double[] deweights = new double[weights.length];
                double ua = weights[0] * 1 + weights[1] * x1 + weights[2] * x2;
                double va = Math.max(ua, 0);
                double ub = weights[3] * 1 + weights[4] * x1 + weights[5] * x2;
                double vb = Math.max(ub, 0);
                double vc = computeOutput(weights, x1, x2);
                double error = (1 / (double) 2) * Math.pow((vc - y), 2);
                double devc = vc - y;
                double deuc = devc * (vc * (1 - vc));
                double deva = weights[7] * deuc;
                double deua = deva;
                if (ua < 0) {
                    deua = 0;
                }
                double devb = weights[8] * deuc;
                double deub = devb;
                if (ub < 0) {
                    deub = 0;
                }
                deweights[0] = 1 * deua;
                deweights[1] = x1 * deua;
                deweights[2] = x2 * deua;
                deweights[3] = 1 * deub;
                deweights[4] = x1 * deub;
                deweights[5] = x2 * deub;
                deweights[6] = 1 * deuc;
                deweights[7] = va * deuc;
                deweights[8] = vb * deuc;

                for (int i = 0; i < weights.length; i++) {
                    if (deweights[i] > -0.000004 && deweights[i] < 0) {
                        deweights[i] *= -1;
                    }
                    System.out.print(df2.format(deweights[i]));
                    if (i != weights.length - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        } else if (args[0].equals("500")) {
            if (args.length != 14) {
                System.out.println("Need areguments for weights, x values, y value, and eta.");
            } else {
                for (int i = 0; i < 9; i++) {
                    weights[i] = Double.parseDouble(args[i + 1]);
                }
                double x1 = Double.parseDouble(args[10]);
                double x2 = Double.parseDouble(args[11]);
                double y = Double.parseDouble(args[12]);
                double eta = Double.parseDouble(args[13]);
                double[] deweights = new double[weights.length];
                double ua = weights[0] * 1 + weights[1] * x1 + weights[2] * x2;
                double va = Math.max(ua, 0);
                double ub = weights[3] * 1 + weights[4] * x1 + weights[5] * x2;
                double vb = Math.max(ub, 0);
                double vc = computeOutput(weights, x1, x2);
                double error = (1 / (double) 2) * Math.pow((vc - y), 2);
                double devc = vc - y;
                double deuc = devc * (vc * (1 - vc));
                double deva = weights[7] * deuc;
                double deua = deva;
                if (ua < 0) {
                    deua = 0;
                }
                double devb = weights[8] * deuc;
                double deub = devb;
                if (ub < 0) {
                    deub = 0;
                }
                deweights[0] = 1 * deua;
                deweights[1] = x1 * deua;
                deweights[2] = x2 * deua;
                deweights[3] = 1 * deub;
                deweights[4] = x1 * deub;
                deweights[5] = x2 * deub;
                deweights[6] = 1 * deuc;
                deweights[7] = va * deuc;
                deweights[8] = vb * deuc;

                double[] newWeights = new double[weights.length];
                for (int i = 0; i < newWeights.length; i++) {
                    newWeights[i] = weights[i] - (eta * deweights[i]);
                }

                double newvc = computeOutput(newWeights, x1, x2);
                double newError = (1 / (double) 2) * Math.pow((newvc - y), 2);

                for (int i = 0; i < weights.length; i++) {
                    if (weights[i] > -0.000004 && weights[i] < 0) {
                        weights[i] *= -1;
                    }
                    System.out.print(df2.format(weights[i]));
                    if (i != weights.length - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
                System.out.println(df2.format(error));
                for (int i = 0; i < newWeights.length; i++) {
                    if (newWeights[i] > -0.000004 && newWeights[i] < 0) {
                        newWeights[i] *= -1;
                    }
                    System.out.print(df2.format(newWeights[i]));
                    if (i != newWeights.length - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
                System.out.println(df2.format(newError));
            }
        } else if (args[0].equals("600")) {
            if (args.length != 11) {
                System.out.println("Need areguments for weights and eta.");
            } else {
                for (int i = 0; i < 9; i++) {
                    weights[i] = Double.parseDouble(args[i + 1]);
                }
                double eta = Double.parseDouble(args[10]);

                File file = new File("hw2_midterm_A_train.txt");
                File file2 = new File("hw2_midterm_A_eval.txt");
                try {
                    Scanner scnr = new Scanner(file);
                    double[][] data = new double[67][3];
                    String s;
                    String[] sSplit = new String[3];
                    for (int i = 0; i < 67; i++) {
                        s = scnr.nextLine();
                        sSplit = s.split(" ");
                        for (int j = 0; j < 3; j++) {
                            data[i][j] = Double.parseDouble(sSplit[j]);
                        }
                    }
                    Scanner scnr2 = new Scanner(file2);
                    double[][] evalScores = new double[25][3];
                    for (int i = 0; i < 25; i++) {
                        s = scnr2.nextLine();
                        sSplit = s.split(" ");
                        for (int j = 0; j < 3; j++) {
                            evalScores[i][j] = Double.parseDouble(sSplit[j]);
                        }
                    }

                    double[][] newWeights = new double[67][9];
                    double[] evalError = new double[67];

                    double ua;
                    double va;
                    double ub;
                    double vb;
                    double vc;
                    double error;
                    double devc;
                    double deuc;
                    double deva;
                    double deua;
                    double devb;
                    double deub;

                    for (int i = 0; i < 67; i++) {
                        double x1 = data[i][0];
                        double x2 = data[i][1];
                        double y = data[i][2];

                        if (i == 0) {
                            ua = weights[0] * 1 + weights[1] * x1 + weights[2] * x2;
                            va = Math.max(ua, 0);
                            ub = weights[3] * 1 + weights[4] * x1 + weights[5] * x2;
                            vb = Math.max(ub, 0);
                            vc = computeOutput(weights, x1, x2);
                            error = (1 / (double) 2) * Math.pow((vc - y), 2);
                            devc = vc - y;
                            deuc = devc * (vc * (1 - vc));
                            deva = weights[7] * deuc;
                            deua = deva;
                            if (ua < 0) {
                                deua = 0;
                            }
                            devb = weights[8] * deuc;
                            deub = devb;
                            if (ub < 0) {
                                deub = 0;
                            }
                        } else {
                            ua = newWeights[i-1][0] * 1 + newWeights[i-1][1] * x1 + newWeights[i-1][2] * x2;
                            va = Math.max(ua, 0);
                            ub = newWeights[i-1][3] * 1 + newWeights[i-1][4] * x1 + newWeights[i-1][5] * x2;
                            vb = Math.max(ub, 0);
                            vc = computeOutput(newWeights[i-1], x1, x2);
                            error = (1 / (double) 2) * Math.pow((vc - y), 2);
                            devc = vc - y;
                            deuc = devc * (vc * (1 - vc));
                            deva = newWeights[i-1][7] * deuc;
                            deua = deva;
                            if (ua < 0) {
                                deua = 0;
                            }
                            devb = newWeights[i-1][8] * deuc;
                            deub = devb;
                            if (ub < 0) {
                                deub = 0;
                            }
                        }

                        double[] deweights = new double[weights.length];
                        deweights[0] = 1 * deua;
                        deweights[1] = x1 * deua;
                        deweights[2] = x2 * deua;
                        deweights[3] = 1 * deub;
                        deweights[4] = x1 * deub;
                        deweights[5] = x2 * deub;
                        deweights[6] = 1 * deuc;
                        deweights[7] = va * deuc;
                        deweights[8] = vb * deuc;

                        for (int j = 0; j < newWeights[i].length; j++) {
                            if (i == 0) {
                                newWeights[i][j] = weights[j] - (eta * deweights[j]);
                            } else {
                                newWeights[i][j] = newWeights[i - 1][j] - (eta * deweights[j]);
                            }
                        }

                        evalError[i] = 0;
                        for (int j = 0; j < 25; j++) {
                            double newvc = computeOutput(newWeights[i], evalScores[j][0], evalScores[j][1]);
                            evalError[i] += (1 / (double) 2) * Math.pow((newvc - evalScores[j][2]), 2);
                        }

                    }


                    for (int i = 0; i < 67; i++) {
                        System.out.println(df2.format(data[i][0]) + " " + df2.format(data[i][1]) + " " + df2.format(data[i][2]));
                        for (int j = 0; j < newWeights[i].length; j++) {
                            if (newWeights[i][j] > -0.000004 && newWeights[i][j] < 0) {
                                newWeights[i][j] *= -1;
                            }
                            System.out.print(df2.format(newWeights[i][j]));
                            if (j != newWeights[i].length - 1) {
                                System.out.print(" ");
                            }
                        }
                        System.out.println();
                        System.out.println(df2.format(evalError[i]));
                    }

                } catch (Exception e) {
                    System.out.println("Reading from file failed.");
                }

            }
        } else if (args[0].equals("700")) {
            if (args.length != 12) {
                System.out.println("Need areguments for weights, eta and T.");
            } else {
                for (int i = 0; i < 9; i++) {
                    weights[i] = Double.parseDouble(args[i + 1]);
                }
                double eta = Double.parseDouble(args[10]);
                int T = Integer.parseInt(args[11]);

                File file = new File("hw2_midterm_A_train.txt");
                File file2 = new File("hw2_midterm_A_eval.txt");
                try {
                    Scanner scnr = new Scanner(file);
                    double[][] data = new double[67][3];
                    String s;
                    String[] sSplit = new String[3];
                    for (int i = 0; i < 67; i++) {
                        s = scnr.nextLine();
                        sSplit = s.split(" ");
                        for (int j = 0; j < 3; j++) {
                            data[i][j] = Double.parseDouble(sSplit[j]);
                        }
                    }
                    Scanner scnr2 = new Scanner(file2);
                    double[][] evalScores = new double[25][3];
                    for (int i = 0; i < 25; i++) {
                        s = scnr2.nextLine();
                        sSplit = s.split(" ");
                        for (int j = 0; j < 3; j++) {
                            evalScores[i][j] = Double.parseDouble(sSplit[j]);
                        }
                    }

                    double[][][] newWeights = new double[T][67][9];
                    double[][] evalError = new double[T][67];

                    double ua;
                    double va;
                    double ub;
                    double vb;
                    double vc;
                    double error;
                    double devc;
                    double deuc;
                    double deva;
                    double deua;
                    double devb;
                    double deub;

                    for (int h = 0; h < T; h++) {
                        for (int i = 0; i < 67; i++) {
                            double x1 = data[i][0];
                            double x2 = data[i][1];
                            double y = data[i][2];

                            if (h == 0 && i == 0) {
                                ua = weights[0] * 1 + weights[1] * x1 + weights[2] * x2;
                                va = Math.max(ua, 0);
                                ub = weights[3] * 1 + weights[4] * x1 + weights[5] * x2;
                                vb = Math.max(ub, 0);
                                vc = computeOutput(weights, x1, x2);
                                error = (1 / (double) 2) * Math.pow((vc - y), 2);
                                devc = vc - y;
                                deuc = devc * (vc * (1 - vc));
                                deva = weights[7] * deuc;
                                deua = deva;
                                if (ua < 0) {
                                    deua = 0;
                                }
                                devb = weights[8] * deuc;
                                deub = devb;
                                if (ub < 0) {
                                    deub = 0;
                                }
                            } else if (i == 0){
                                ua = newWeights[h-1][66][0] * 1 + newWeights[h-1][66][1] * x1 + newWeights[h-1][66][2] * x2;
                                va = Math.max(ua, 0);
                                ub = newWeights[h-1][66][3] * 1 + newWeights[h-1][66][4] * x1 + newWeights[h-1][66][5] * x2;
                                vb = Math.max(ub, 0);
                                vc = computeOutput(newWeights[h-1][66], x1, x2);
                                error = (1 / (double) 2) * Math.pow((vc - y), 2);
                                devc = vc - y;
                                deuc = devc * (vc * (1 - vc));
                                deva = newWeights[h-1][66][7] * deuc;
                                deua = deva;
                                if (ua < 0) {
                                    deua = 0;
                                }
                                devb = newWeights[h-1][66][8] * deuc;
                                deub = devb;
                                if (ub < 0) {
                                    deub = 0;
                                }
                            } else {
                                ua = newWeights[h][i-1][0] * 1 + newWeights[h][i-1][1] * x1 + newWeights[h][i-1][2] * x2;
                                va = Math.max(ua, 0);
                                ub = newWeights[h][i-1][3] * 1 + newWeights[h][i-1][4] * x1 + newWeights[h][i-1][5] * x2;
                                vb = Math.max(ub, 0);
                                vc = computeOutput(newWeights[h][i-1], x1, x2);
                                error = (1 / (double) 2) * Math.pow((vc - y), 2);
                                devc = vc - y;
                                deuc = devc * (vc * (1 - vc));
                                deva = newWeights[h][i-1][7] * deuc;
                                deua = deva;
                                if (ua < 0) {
                                    deua = 0;
                                }
                                devb = newWeights[h][i-1][8] * deuc;
                                deub = devb;
                                if (ub < 0) {
                                    deub = 0;
                                }
                            }

                            double[] deweights = new double[weights.length];
                            deweights[0] = 1 * deua;
                            deweights[1] = x1 * deua;
                            deweights[2] = x2 * deua;
                            deweights[3] = 1 * deub;
                            deweights[4] = x1 * deub;
                            deweights[5] = x2 * deub;
                            deweights[6] = 1 * deuc;
                            deweights[7] = va * deuc;
                            deweights[8] = vb * deuc;

                            for (int j = 0; j < newWeights[h][i].length; j++) {
                                if (h == 0 && i == 0) {
                                    newWeights[h][i][j] = weights[j] - (eta * deweights[j]);
                                } else if (i == 0) {
                                    newWeights[h][i][j] = newWeights[h-1][66][j] - (eta * deweights[j]);
                                } else {
                                    newWeights[h][i][j] = newWeights[h][i - 1][j] - (eta * deweights[j]);
                                }
                            }

                            evalError[h][i] = 0;
                            for (int j = 0; j < 25; j++) {
                                double newvc = computeOutput(newWeights[h][i], evalScores[j][0], evalScores[j][1]);
                                evalError[h][i] += (1 / (double) 2) * Math.pow((newvc - evalScores[j][2]), 2);
                            }

                        }
                    }

                    double[][] epochEndWeights = new double[T][9];
                    double[] epochEndError = new double[T];

                    for (int i = 0; i < T; i++) {
                        epochEndWeights[i] = newWeights[i][66];
                        epochEndError[i] = evalError[i][66];
                    }

                    for (int i = 0; i < T; i++) {
                        for (int j = 0; j < epochEndWeights[i].length; j++) {
                            if (epochEndWeights[i][j] > -0.000004 && epochEndWeights[i][j] < 0) {
                                epochEndWeights[i][j] *= -1;
                            }
                            System.out.print(df2.format(epochEndWeights[i][j]));
                            if (j != epochEndWeights[i].length - 1) {
                                System.out.print(" ");
                            }
                        }
                        System.out.println();
                        System.out.println(df2.format(epochEndError[i]));
                    }

                } catch (Exception e) {
                    System.out.println("Reading from file failed.");
                }

            }
        } else if (args[0].equals("800")) {
            if (args.length != 12) {
                System.out.println("Need areguments for weights, eta and T.");
            } else {
                for (int i = 0; i < 9; i++) {
                    weights[i] = Double.parseDouble(args[i + 1]);
                }
                double eta = Double.parseDouble(args[10]);
                int T = Integer.parseInt(args[11]);

                File file = new File("hw2_midterm_A_train.txt");
                File file2 = new File("hw2_midterm_A_eval.txt");
                File file3 = new File("hw2_midterm_A_test.txt");
                try {
                    Scanner scnr = new Scanner(file);
                    double[][] data = new double[67][3];
                    String s;
                    String[] sSplit = new String[3];
                    for (int i = 0; i < 67; i++) {
                        s = scnr.nextLine();
                        sSplit = s.split(" ");
                        for (int j = 0; j < 3; j++) {
                            data[i][j] = Double.parseDouble(sSplit[j]);
                        }
                    }
                    Scanner scnr2 = new Scanner(file2);
                    double[][] evalScores = new double[25][3];
                    for (int i = 0; i < 25; i++) {
                        s = scnr2.nextLine();
                        sSplit = s.split(" ");
                        for (int j = 0; j < 3; j++) {
                            evalScores[i][j] = Double.parseDouble(sSplit[j]);
                        }
                    }
                    Scanner scnr3 = new Scanner(file3);
                    double[][] testScores = new double[25][3];
                    for (int i = 0; i < 25; i++) {
                        s = scnr3.nextLine();
                        sSplit = s.split(" ");
                        for (int j = 0; j < 3; j++) {
                            testScores[i][j] = Double.parseDouble(sSplit[j]);
                        }
                    }

                    double[][][] newWeights = new double[T][67][9];
                    double[][] evalError = new double[T][67];

                    for (int h = 0; h < T; h++) {
                        double ua;
                        double va;
                        double ub;
                        double vb;
                        double vc;
                        double error;
                        double devc;
                        double deuc;
                        double deva;
                        double deua;
                        double devb;
                        double deub;

                        for (int i = 0; i < 67; i++) {
                            double x1 = data[i][0];
                            double x2 = data[i][1];
                            double y = data[i][2];

                            if (h == 0 && i == 0) {
                                ua = weights[0] * 1 + weights[1] * x1 + weights[2] * x2;
                                va = Math.max(ua, 0);
                                ub = weights[3] * 1 + weights[4] * x1 + weights[5] * x2;
                                vb = Math.max(ub, 0);
                                vc = computeOutput(weights, x1, x2);
                                error = (1 / (double) 2) * Math.pow((vc - y), 2);
                                devc = vc - y;
                                deuc = devc * (vc * (1 - vc));
                                deva = weights[7] * deuc;
                                deua = deva;
                                if (ua < 0) {
                                    deua = 0;
                                }
                                devb = weights[8] * deuc;
                                deub = devb;
                                if (ub < 0) {
                                    deub = 0;
                                }
                            } else if (i == 0){
                                ua = newWeights[h-1][66][0] * 1 + newWeights[h-1][66][1] * x1 + newWeights[h-1][66][2] * x2;
                                va = Math.max(ua, 0);
                                ub = newWeights[h-1][66][3] * 1 + newWeights[h-1][66][4] * x1 + newWeights[h-1][66][5] * x2;
                                vb = Math.max(ub, 0);
                                vc = computeOutput(newWeights[h-1][66], x1, x2);
                                error = (1 / (double) 2) * Math.pow((vc - y), 2);
                                devc = vc - y;
                                deuc = devc * (vc * (1 - vc));
                                deva = newWeights[h-1][66][7] * deuc;
                                deua = deva;
                                if (ua < 0) {
                                    deua = 0;
                                }
                                devb = newWeights[h-1][66][8] * deuc;
                                deub = devb;
                                if (ub < 0) {
                                    deub = 0;
                                }
                            } else {
                                ua = newWeights[h][i-1][0] * 1 + newWeights[h][i-1][1] * x1 + newWeights[h][i-1][2] * x2;
                                va = Math.max(ua, 0);
                                ub = newWeights[h][i-1][3] * 1 + newWeights[h][i-1][4] * x1 + newWeights[h][i-1][5] * x2;
                                vb = Math.max(ub, 0);
                                vc = computeOutput(newWeights[h][i-1], x1, x2);
                                error = (1 / (double) 2) * Math.pow((vc - y), 2);
                                devc = vc - y;
                                deuc = devc * (vc * (1 - vc));
                                deva = newWeights[h][i-1][7] * deuc;
                                deua = deva;
                                if (ua < 0) {
                                    deua = 0;
                                }
                                devb = newWeights[h][i-1][8] * deuc;
                                deub = devb;
                                if (ub < 0) {
                                    deub = 0;
                                }
                            }

                            double[] deweights = new double[weights.length];
                            deweights[0] = 1 * deua;
                            deweights[1] = x1 * deua;
                            deweights[2] = x2 * deua;
                            deweights[3] = 1 * deub;
                            deweights[4] = x1 * deub;
                            deweights[5] = x2 * deub;
                            deweights[6] = 1 * deuc;
                            deweights[7] = va * deuc;
                            deweights[8] = vb * deuc;

                            for (int j = 0; j < newWeights[h][i].length; j++) {
                                if (h == 0 && i == 0) {
                                    newWeights[h][i][j] = weights[j] - (eta * deweights[j]);
                                } else if (i == 0) {
                                    newWeights[h][i][j] = newWeights[h-1][66][j] - (eta * deweights[j]);
                                } else {
                                    newWeights[h][i][j] = newWeights[h][i - 1][j] - (eta * deweights[j]);
                                }
                            }

                            evalError[h][i] = 0;
                            for (int j = 0; j < 25; j++) {
                                double newvc = computeOutput(newWeights[h][i], evalScores[j][0], evalScores[j][1]);
                                evalError[h][i] += (1 / (double) 2) * Math.pow((newvc - evalScores[j][2]), 2);
                            }

                        }
                    }

                    double[][] epochEndWeights = new double[T][9];
                    double[] epochEndError = new double[T];
                    int bestIndex = 0;

                    for (int i = 0; i < T; i++) {
                        epochEndWeights[i] = newWeights[i][66];
                        epochEndError[i] = evalError[i][66];
                    }

                    double currEndError = epochEndError[1];
                    double prevEndError = epochEndError[0];

                    for (int i = 1; i < T; i++) {
                        if (currEndError > prevEndError) {
                            bestIndex = i;
                            break;
                        } else if (i == T - 1) {
                            bestIndex = T - 1;
                            break;
                        } else {
                            prevEndError = currEndError;
                            currEndError = epochEndError[i + 1];
                        }
                    }


                    System.out.println(bestIndex + 1);
                    for (int j = 0; j < epochEndWeights[bestIndex].length; j++) {
                        if (epochEndWeights[bestIndex][j] > -0.000004 && epochEndWeights[bestIndex][j] < 0) {
                            epochEndWeights[bestIndex][j] *= -1;
                        }
                        System.out.print(df2.format(epochEndWeights[bestIndex][j]));
                        if (j != epochEndWeights[bestIndex].length - 1) {
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                    System.out.println(df2.format(epochEndError[bestIndex]));

                    // Compute Test Classification Accuracy
                    double[] finalWeights = epochEndWeights[bestIndex];
                    int numberCorrect = 0;
                    for (int i = 0; i < 25; i++) {
                        double x1 = testScores[i][0];
                        double x2 = testScores[i][1];
                        double y = testScores[i][2];
                        double vc = computeOutput(finalWeights, x1, x2);
                        if (vc >= (1 / (double) 2)) {
                            vc = 1;
                        } else {
                            vc = 0;
                        }
                        if (vc == y) {
                            numberCorrect++;
                        }
                    }
                    double accuracy = numberCorrect / (double) 25;

                    System.out.println(df2.format(accuracy));

                } catch (Exception e) {
                    System.out.println("Reading from file failed.");
                }

            }
        }
    }

}
