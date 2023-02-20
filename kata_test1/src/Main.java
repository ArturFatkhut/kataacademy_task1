import java.util.Arrays;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str = scan.nextLine();
        System.out.println(calc(str));

    }

    /*public static boolean checkArabic(String[] input) throws Exception {
        boolean check = false;
        int a = 0;
        int b = 0;

        try
        {
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[2]);
        }
        catch (Exception e)
        {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        for (int i = 0; i < dict.arab.length; i++)
        {
            if(a == Integer.parseInt(dict.arab[i]) || b == Integer.parseInt(dict.arab[i]))
            {
                check = true;
            }
        }

        return check;
    }*/

    public static boolean checkRoman(String input) throws Exception
    {
        boolean check = false;
        boolean checkExists = false;
        for (int i = 0; i < dict.rome.length; i++)
        {
            if(input.equals(dict.rome[i]))
            {
                check = true;
                checkExists = true;
            }

        }
        if (!checkExists && !Arrays.asList(dict.arab).contains(input))
        {
            throw new Exception("Можно использовать числа только от I до X");
        }
        else if(!Arrays.asList(dict.arab).contains(input) && !Arrays.asList(dict.rome).contains(input))
        {
            throw new Exception("Можно использовать только арабские или римские числа");
        }
        return check;
    }
    /*
    public static boolean checkRoman(String[] input)
    {
        char a = input[0].charAt(0);
        char b = input[2].charAt(0);
        boolean check = false;

        for (int i = 0; i < dict.rome.length; i++)
        {
            if(a == dict.rome[i].charAt(0) || b == dict.rome[i].charAt(0))
            {
                check = true;
            }
        }
        return check;
    }*/
    public static String calc(String input) throws Exception
    {

        String[] cuts = input.split(" ");
        int a = 0;
        int b = 0;
        int result = 0;
/*

        if(checkRoman(cuts[0]) && checkRoman(cuts[2]))
        {
            if(checkRoman(cuts))
            {
                throw new Exception("используются одновременно разные системы счисления");
            }

        }
        else if(!checkRoman(cuts))
        {
            if(checkArabic(cuts))
            {
                throw new Exception("используются одновременно разные системы счисления");
            }
        }*/
        if (!input.contains(" "))
        {
            throw new Exception("Неправильный ввод данных. Пример: 7 * 4");
        }

        if (!checkRoman(cuts[0]) && !checkRoman(cuts[2]))
        {
            a = Integer.parseInt(cuts[0]);
            b = Integer.parseInt(cuts[2]);
            if(a > 10 || a < 1 || b > 10 || b < 1)
            {
                throw new Exception("Можно использовать числа только от 1 до 10");
            }
        }
        else if (checkRoman(cuts[0]) && checkRoman(cuts[2]))
        {
            a = dict.converter(cuts[0]);
            b = dict.converter(cuts[2]);
        }
        else
        {
            throw new Exception("Используются одновременно разные системы счисления");
        }


        char operator = cuts[1].charAt(0);

        if(cuts.length > 3)
        {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }


        switch (operator)
        {
            case ('+'):
                result = a + b;
                break;
            case ('-'):
                result = a - b;
                break;
            case ('*'):
                result = a * b;
                break;
            case ('/'):
                try
                {
                    result = a / b;
                    break;
                }
                catch (Exception e)
                {
                    throw new Exception("На ноль делить нельзя");
                }
            default:
                throw new Exception("Поддерживается такая арифметическиая операция");
        }

        if(checkRoman(cuts[0]) && checkRoman(cuts[2]))
        {
            if(result < 1)
            {
                throw new Exception("Римские числа не могут быть меньше I");
            }
            String r = dict.arabicToRoman(result);
            return r;
        }
        String res = Integer.toString(result);
        return res;
    }
}
class dict
{

    static String[] arab = {"1","2","3","4","5","6","7","8","9","10"};
    static String[] rome = {"I","II","III","IV","V","VI","VII","VIII","IX", "X"};

    public static int converter(String a) throws Exception
    {
        int temp = 0;
        boolean checkExists = false;
        //char b = a.charAt(0);
        for (int i = 0; i < arab.length; i++)
        {

            if (a.equals(rome[i]))
            {
                temp = Integer.parseInt(arab[i]);
                checkExists = true;
            }

        }
        return temp;
    }

    /*public static String deConverter(int a)
    {
        String res = "";
        for (int i = 0; i < rome.length; i++)
        {
            if (a == Integer.parseInt(arab[i]))
            {
                res = rome[i];
            }
        }

        return res;
    }*/
    public static String arabicToRoman(int num)
    {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();

        for(int i=0;i<values.length;i++)
        {
            while(num >= values[i])
            {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }
}