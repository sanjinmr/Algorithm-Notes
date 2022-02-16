package com.sanjin.www.algorithm.selectsort.heapsort;

/**
 * 大根堆的其他实现
 */
public class MaxHeapSort1 {
	
	// =============== S=大根堆排序 ===========================

    protected int parent(int i) {return (i - 1) / 2;}

    protected int left(int i) {return 2 * i + 1;}

    protected int right(int i) {return 2 * i + 2;}

    // 建立大根堆的方法：

    /**
     * 将元素array[parent]自下往上逐步调整树形结构
     * @param array
     * @param parent
     * @param length
     */
    private void maxHeapAdjust0(int[] array, int parent, int length) {
        for(int child = left(parent); child < length; child = left(child)) {
            // child为初始化为节点parent的左孩子，沿节点较大的子节点向下调整
            if(child + 1 < length && array[child] < array[child + 1]) {
                // 取节点较大的子节点的下标
                // 如果节点的右孩子>左孩子，则取右孩子节点的下标
                child ++;
            }
            if(array[parent] >= array[child]){
                // 根节点 >=左右子女中关键字较大者，调整结束
                break;
            }else{
                // 根节点 <左右子女中关键字较大者
                // 将左右子结点中较大值array[i]调整到双亲节点上
                int temp = array[parent];
                array[parent] = array[child];
                array[child] = temp;

                //【关键】修改parent值，以便继续向下调整
                parent = child;
            }
        }
    }

    /**
     * 优化maxHeapAdjust0，每一次遍历都交互parent和child，浪费了时间和空间。
     * 减少交换，优化为赋值。
     * 将元素array[parent]自下往上逐步调整树形结构
     * @param array
     * @param parent
     * @param length
     */
    private void maxHeapAdjust(int[] array, int parent, int length) {
        int temp = array[parent];
        for(int child = left(parent); child < length; child = left(child)) {    
			// child为初始化为节点parent的左孩子，沿节点较大的子节点向下调整
            if(child + 1 < length && array[child] < array[child + 1]) {  
				// 取节点较大的子节点的下标
                // 如果节点的右孩子>左孩子，则取右孩子节点的下标
				child ++;
            }
            if(temp >= array[child]){  
				// 根节点 >=左右子女中关键字较大者，调整结束
                break;
            }else{   
				// 根节点 <左右子女中关键字较大者
				// 将左右子结点中较大值array[i]调整到双亲节点上
                array[parent] = array[child];
				//【关键】修改parent值，以便继续向下调整
                parent = child;
            }
        }
		// 被调整的结点的值放入最终位置
        array[parent] = temp;
    }
	
	// 构建大根堆：将array看成完全二叉树的顺序存储结构
    private int[] buildMaxHeap(int[] array) {
        // 从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
        for(int i = parent(array.length - 1); i >= 0; i --) {
			// 从尾部递增向上排序：大的上去，小的下来，尾部边界是数组末尾
            maxHeapAdjust(array, i, array.length);
        }
        return array;
    }

    // 堆排序
    public int[] heapSort(int[] array) {
		// 初始建堆，array[0]为第一趟值最大的元素
        array = buildMaxHeap(array);
        for(int i = array.length - 1; i > 0; i --) {
            // 将堆顶元素和堆低元素交换，即得到当前最大元素正确的排序位置
			int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
			// 整理，将剩余的元素整理成堆：每次从最上面开始排序，尾部递减
            maxHeapAdjust(array, 0, i);
        }
        return array;
    }

	// =============== E=大根堆排序 ===========================

    // 删除堆顶元素（即序列中的最大值）：先将堆的最后一个元素与堆顶元素交换，由于此时堆的性质被破坏，需对此时的根节点进行向下调整操作。

    // 删除堆顶元素操作
    public int[] deleteMax(int[] array){
        // 将堆的最后一个元素与堆顶元素交换，堆底元素值设为-99999
        array[0] = array[array.length - 1];
        array[array.length - 1] = -99999;
        // 对此时的根节点进行向下调整
        maxHeapAdjust(array, 0, array.length);
        return array;
    }


    // 对堆的插入操作：先将新节点放在堆的末端，再对这个新节点执行向上调整操作。

    // 假设数组的最后一个元素array[array.length-1]为空，新插入的结点初始时放置在此处。

    // 插入操作:向大根堆array中插入数据data
    public int[] insertData(int[] array, int data){
        // 将新节点放在堆的末端
		array[array.length - 1] = data;
        // 需要调整的节点
		int k = array.length - 1;
        // 双亲节点
		int parent = (k - 1) / 2;
        while(parent >= 0 && data > array[parent]){
            // 双亲节点下调
			array[k] = array[parent];
            k = parent;
            if(parent != 0){
				// 继续向上比较
                parent = (parent - 1) / 2;
            }else{  
				// 根节点已调整完毕，跳出循环
                break;
            }
        }
		// 将插入的结点放到正确的位置
        array[k] = data;
        return array;
    }


    // 测试：

    public void toString(int[] array){
        for(int i:array){
            System.out.print(i+" ");
        }
    }

    public void main(String args[]){
        int[] array = {87,45,78,32,17,65,53,9,122};
        System.out.print("构建大根堆：");
        toString(buildMaxHeap(array));
        System.out.print("\n"+"删除堆顶元素：");
        toString(deleteMax(array));
        System.out.print("\n"+"插入元素63:");
        toString(insertData(array, 63));
        System.out.print("\n"+"大根堆排序：");
        toString(heapSort(array));
    }

    /*
    1 构建大根堆：122 87 78 45 17 65 53 9 32
    2 删除堆顶元素：87 45 78 32 17 65 53 9 -99999
    3 插入元素63:87 63 78 45 17 65 53 9 32
    4 大根堆排序：9 17 32 45 53 63 65 78 87
    */

}
