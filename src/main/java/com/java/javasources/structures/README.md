# 阅读基础要求：
     1.基本概念
     2.大O表示法

# 数据结构和算法分析
  数据结构：某种数值所需的存储结构
  算法：对数值根据所需的计算方法

# 复杂度分析：如何分析以及统计算法的执行效率和资源消耗
  1.概念解释
    1.1 执行效率，即是时间复杂度
    1.2 资源消耗，即是空间复杂度
   
  2.事后统计法
    事先写好代码运行一遍统计执行效率
    FAQ:
       1.受测试环境的影响，i9肯定比i3执行效率高吧
       2.受运行的数据量的影响，一般测试的数据量可控，正式运行的数据量不完全可控
  
  *以下代码仅用于辅助理解概念*
  
  3.表示法 
    3.1 大O表示法
        O(1)、O(log^n)、O(n)、O(nlog^n)、O(n^2)、O(n^3)、O(2^n)
      
        具体表示概念请自行学习：
     
        比如：
        
            O(1)
             int i = 0;
             int j = 1;
             
            O(n)
             for(int i =0;i < n;i++){
                int a = i + 1;
                System.out.println("\"" + a)
             } 
             
    3.2 最优时间复杂度
        int j = 0
        for(int i = 0;i < n;i++){
           if (j == i) {
             System.out.println();
           }
        }
        
    3.3 最差时间复杂度
        int j = n - 1 
        for(int i = 0;i < n;i++){
           if (j == n) {
             System.out.println();
           }
        }
        
    3.4 平均时间复杂度(结合简单的'概率论'理解)
    int j = m
    for(int i = 0;i < n;i++){
       if (j == n) {
         System.out.println();
       }
       break;
    }
    
    3.5 均摊时间复杂度  
    int[] arrary = new int[n];
    int c = 0;
    void add(int v) {
       if (c == arraylength) {
            int s = 0;
            for (int i = 0;i < array.lengthi++) {
                s = s + array[i];
            }
            array[0] = s;
            c = 1;
       }
       array[c] = v;
       ++c;
    } 
