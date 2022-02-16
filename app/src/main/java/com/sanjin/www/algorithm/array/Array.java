package com.sanjin.www.algorithm.array;

/**
 * 数组
 * D: 短整形即int
 * S: 连续的线性数据结构
 * P: 增、删、随机访问
 *
 * note:
 * 1.参考了https://github.com/wangzheng0822/algo.git
 * 2. 本Array对象不用于java.lang.reflect.Array。
 * 可以说本Array是严格根据DSP在定义一个数组数据结构。
 * java.lang.reflect.Array是定义了一些不同数据类型的数组的一些操作的封装。
 */
public class Array {

    //定义整型数据data保存数据
    private int[] data;
    //定义数组长度
    private int n;
    //定义数组中实际个数
    private int count;

    //构造方法，定义数组大小
    private Array(int capacity) {
        this.data = new int[capacity];
        this.n = capacity;
        this.count = 0;//一开始一个数都没有存所以为0
    }

    // 增：插入元素
    public boolean insert(int index, int value) {
        // 检查是否还有空位置
        if (count >= n) {
            System.out.println("没有可插入的位置");
            return false;
        }

        // 检查插入的位置是否合法：两个边界
        // 不能在负数位置插入；不能插入当前已装数据以外的空间，防止空位置
        if (index < 0 || index > count) {
            System.out.println("位置不合法");
            return false;
        }

        // 开始插入，有三种情况：插入在已有数据头部，插入在已有数据尾部，插入在已有数据中间

        // 插入头部，后面的所有数据要往后移一位
        // 因为要覆盖后面的，所以索引从大到小遍历
        if (0 == index) {
            // i表示被移动的数据索引
            for (int i = count - 1; i >= 0; i --) {
                data[i + 1] = data[i];
            }
            data[0] = value;
        }

        // 插入尾部，前面的不变
        if (count == index) {
            data[count] = value;
        }


        // 插入已有数据中,把index后面的数据往后移动
        // 因为要覆盖后面的，所以索引从大到小遍历
        if (0 < index && index < count) {
            for (int i = count - 1; i >= index; i --) {
                data[i + 1] = data[i];
            }
            data[index] = value;
        }

        count ++;
        return true;
    }

    // 删：根据索引删除元素
    public boolean delete(int index) {
        // 检查删除的位置是否合法
        if (index < 0 || index >= count) {
            return false;
        }

        // 如果数组中没有数据，则不用删除
        // 这个其实在检查合法性的时候已经包含了
        if (count == 0) {
            System.out.println("不能删除一个空数组");
            return false;
        }

        // 删除的位置：删除头、删除尾部、删除中间

        // 删除头：后面的数据需要前移
        // 因为要覆盖前面的，所以索引从小到大遍历
        if (0 == index) {
            // i表示被移动的数据索引
            for (int i = index + 1; i < count; i ++) {
                data[i - 1] = data[i];
            }
        }

        // 删除尾：不用移动数据,这里将已有数据设为0
        // 其实只要count减一，这里啥都不做就行，毕竟0也是一直数据
        // new int[10]得到的数组，默认每个元素值为0
        if (count - 1 == index) {
            data[count - 1] = 0;
        }

        // 删除中间数据: 将后面的元素往前移动
        // 因为要覆盖前面的，所以索引从小到大遍历
        if (0 < index && index < count - 1) {
            for (int i = index + 1; i < count; i ++) {
                data[i - 1] = data[i];
            }
        }


        // 使count非负数
        count --;
        if (count < 0) {
            count = 0;
        }

        return true;
    }

    //查：根据索引，找到数据中的元素并返回
    public int find(int index) {
        // 检查索引是否合法
        if (index < 0 || index >= count) {
            // 索引不合法时返回-1
            return -1;
        }

        // 根据索引返回数据
        return data[index];
    }

}
