//
// Created by Administrator on 2019/5/29.
//

/**
    C++ 演示快速排序算法
    一趟快速排序的具体做法是：附设两个指针low和high，它们的初值分别为low和high，设枢轴记录的关键字
    为pivotkey，则首先从high所指位置起向前搜索找到第一个关键字小于pivotkey的记录和枢轴记录互相交换，
    然后从low所指位置起向后搜索，找到第一个关键字大于pivotkey的记录和枢轴记录互相交换，重复这两步
    直至low=high为止。算法如下：
**/
int Partition (SqList &L, int low, int high) {
    // 交换顺序表L中子表L.r[low.high]的记录，使枢轴记录到位，并返回其所在位置，此时
    // 在它之前（后）的记录均不大（小）于它

    // 用子表的第一个记录作枢轴记录
    pivotkey = L.r[low].key;

    // 从表的两端交替地向中间扫描
    while (low < high) {
        while (low < high && L.r[high].key >= pivotkey) -- high;
        // 将比枢轴记录小的记录交换到低端
        L.r[low] <--> L.r[high];
        while (low < high && L.r[low].key <= pivotkey) ++ low;
        // 将比枢轴记录大的记录交换到高端
        L.r[low] <--> L.r[high];
    }
    // 返回枢轴所在位置
    return low;
}


/**
1、具体实现上述算法时，每交换一对记录需进行3次记录移动（赋值）的操作
而实际上，在排序过程中对枢轴记录的赋值是多余的，因为只有在一趟排序结束时，即low==high的位置
才是枢轴记录的最后位置。由此可以改写成上述算法，先将枢轴记录暂存在r[0]的位置上，排序过程中只作r[low]
或r[high]的单向移动。直至一趟排序结束后再将枢轴记录移至正确位置上。
2、note: 以左边作为枢轴的时候，要先检查右边；以右边作为枢轴的时候，要先检查左边。为什么？
因为，以左边为枢轴的时候，如果先遍历左边，low必然会停留在一个比枢轴大的位置，
而在遍历右边的时候，若high=low，则low可能会被认定为中间位置。从而导致，最后交换数据的时候，
会把一个大值和枢轴交换，即将大值放到了枢轴左边。举例：
6 1 2 5 4 12 9 7 10 8  （pivot = 6）
以左边开始遍历，会停留在12的位置，并以此为中间点。再和枢轴交换后为：
12 1 2 5 4 6 9 7 10 8
可见，以左边为枢轴时，要先遍历右边，先定一个比枢轴小的为中间点。
反之，以右侧为枢轴时，亦然。
3、快速具体算法如下：
**/
int Partition(SqList &L, int low, int high) {

    // 交换顺序表L中子表r[low.high]的记录，枢轴记录到位，并返回其所在位置，此时
    // 在它之前（后）的记录均不大（小）于它

    // 用子表的第一个记录作枢轴记录
    L.r[0] = L.r[low];
    // 枢轴记录关键字
    pivotkey = L.r[low].key;
    // 从表的两端交替地向中间扫描
    while (low < high) {
        while (low < high && L.r[high].key >= pivotkey) -- high;
        // 将比枢轴记录小的移到低端
        L.r[low] = L.r[high];
        while (low < high && L.r[low].key <= pivotkey) ++ low;
        // 将比枢轴记录大的记录移到高端
        L.r[high] = L.r[low];
    }
    // 枢轴记录到位
    L.r[low] = L.r[0];
    // 返回枢轴位置
    return low;
}

/**
递归形式的快速排序算法：
**/
void QSort(SqList &L, int low, int high) {
    // 对顺序表L中的子序列L.r[low..high]作快速排序

    // 长度大于1
    if (low < high) {
        // 将L.r[low.high]一分为二
        pivotloc = Partition(L, low, high);
        // 对低子表递归排序，pivotloc是枢轴位置
        QSort(L, low, pivotloc - 1);
        // 对高子表递归排序
        QSort(L, pivotloc + 1, high);
    }
}

void QuickSort(SqList &L) {
    // 对顺序表L作快速排序
    QSort(L, 1, L.length);
}
