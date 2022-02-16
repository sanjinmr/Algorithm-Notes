package com.sanjin.www.algorithm.array;

import java.util.Objects;

/**
 * 通用数组（支持动态扩容和缩容）
 * D: 泛型
 * S: 连续的线性数据结构
 * P: 增、删、随机访问
 *
 * note:
 * 1.参考了https://github.com/wangzheng0822/algo.git
 * 2.可以说本Array是严格根据DSP在定义一个数组数据结构。操作内容只定义了基本的增删改查。
 *
 * @param <T> 数据类型
 */
public class GenericArray<T> {

    //定义泛型数据data保存数据
    private T[] data;
    //定义数组中实际个数
    private int count;

    // 根据传入容量，构造Array
    public GenericArray(int capacity) {
        data = (T[]) new Objects[capacity];
        count = 0;
    }

    // 无参构造方法，默认数组容量为10
    public GenericArray() {
        this(10);
    }


    // 先定义主要方法：增、删、改、查

    /**
     * 增:按照索引增加
     * @param index
     */
    public void add(int index, T value) {
        // 检查是否还有空位置：
        // 如果检查数据满了，则直接先扩容
        if (count == data.length) {
            resize(2 * data.length);
        }

        // 检查索引是否合法
        if (index < 0 || index > count) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index < size.");
        }

        // 真正开始插入了：在头、在尾、在中间

        // 在头插入:需要把后面的数据往后移，时间复杂度为O(n)
        // 往后移会覆盖后面的数据，则先移动后面的即索引大处开始移动
        if (0 == index) {
            // i表示要移动的元素索引
            for (int i = count - 1; i >= 0; i --) {
                data[i + 1] = data[i];
            }
            data[0] = value;
        }

        // 在尾插入：不需要搬迁数据，时间复杂度为O(1)
        if (count == index) {
            data[count] = value;
        }

        // 在中间插入：需要将index后面的数据后移，时间复杂度O(1)
        if (0 < index && index < count) {
            for (int i = count - 1; i >= 0; i --) {
                data[i + 1] = data[i];
            }
            data[index] = value;
        }

        count ++;
    }

    /**
     * 删：安装索引删除元素
     * @param index
     * @return
     */
    public T remove(int index) {
        // 检查索引是否合法
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("remove failed! Require index >=0 and index < size.");
        }

        // 开始真正的remove：在头、在尾、在中间
        T ret = data[index];

        // 如果删除的头:将后面的往前移动，时间复杂度O(n)
        // 覆盖的是前面的元素，则先移动前面的数据，即从索引小处开始
        if (0 == index) {
            // i表示被移动的数据的索引
            for (int i = index + 1; i < count; i ++) {
                data[i - 1] = data[i];
            }
        }

        // 如果删除的是尾：不需要移动数据，时间复杂度是O(1)
        if (count - 1 == index) {
            data[count - 1] = null;
        }

        // 如果删除的是中间:将index的数据往前移，时间复杂度O(1)
        if (0 < index && index < count - 1) {
            for (int i = index + 1; i < count; i ++) {
                data[i - 1] = data[i];
            }
        }

        count --;
        if (count < 0) {
            count = 0;
        }

        // 删除后检查是否需要缩容
        // 需要判断新的capacity大于0
        if (count < data.length / 4 && data.length / 2 > 0) {
            resize(data.length / 2);
        }

        return ret;
    }

    /**
     * 改：根据索引修改数组已有元素的值
     * @param index
     * @param value
     */
    public void set(int index, T value) {
        // 检查索引是否合法
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("set failed! Require index >=0 and index < size.");
        }

        data[index] = value;
    }

    /**
     * 改：根据索引查询数组已有元素
     * @param index
     * @return
     */
    public T get(int index) {
        // 检查索引合法性
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("get failed! Require index >=0 and index < size.");
        }

        return data[index];
    }

    // =============== help function =====================

    /**
     * 扩容，需要数据搬移，时间复杂度为O(n)
     * @param capacity 因为resize是私有方法，capacity不能小于等于0，由调用者自己判断处理
     */
    private void resize(int capacity) {
        T[] dataNew = (T[]) new Objects[capacity];

        for (int i = 0; i < count; i ++) {
            dataNew[i] = data[i];
        }
        data = dataNew;
    }
}
