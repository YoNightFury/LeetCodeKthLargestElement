class KthLargest {

    int k ;
    int[] nums;
    int elementsAdded;
    public KthLargest(int k, int[] nums) {
        // init the k
        this.k = k;
        // init an array of  size k
        this.nums = new int[k];
        
        
        // sort the given array in ascending order
        heapSort(nums);
        
        // populate with k largest elements or given number of elements
        // descending order
        int j = 0;
        for(int i = nums.length-1; i>=0&&j < k; j++ ,i-- ){
            this.nums[j] = nums[i];
        }
        
        // this.nums is populated with j+1 elements
        elementsAdded = j;            
        
        
    }
    
    void makeHeap(int[] nums, int root, int lastLeaf){
        
        
        // set the last parent for the heap
        int lastParent = (lastLeaf+1)/2 - 1 ;
        
        // check for the max child in the sub tree
        int maxChild;
        int left = root*2 + 1;
        int right = root * 2 +2;
        if(right<=lastLeaf) // check if right exists
            maxChild = nums[left] > nums[right] ? left : right;
        else if(left<=lastLeaf) // check if left exists
            maxChild = left;
        else // if neither of them exists then return, happens when single element
            return;
        
        if(nums[maxChild]>nums[root]) // if max child is greater than it's parent then 
        {
            int tmp = nums[maxChild];
            nums[maxChild] = nums[root];
            nums[root] = tmp;
            if(maxChild<lastParent)  // the heap mightbe disturb re correct it if more children are parents
                makeHeap(nums, maxChild, lastLeaf);
                
        }
    }
    
    
    
    
    
    void heapSort(int[] nums){
        // set the last leaf of the tree
        
        int lastLeaf = nums.length - 1;
        
        // now we want to make heap of all parents starting fom the last parent
        
        int lastParent = (lastLeaf+1)/2 - 1;
        
        while(lastParent >= 0){
            makeHeap(nums, lastParent, lastLeaf);
            lastParent--;            
        }
        
        
        // while we reach a single element
        // change the root and last leaf node
        while(lastLeaf >=nums.length-k && lastLeaf> 0){
            int tmp = nums[0];
            nums[0] = nums[lastLeaf];
            nums[lastLeaf] = tmp;
            lastLeaf--;
            // make the heap again
            makeHeap(nums, 0, lastLeaf);
        }
        
        // array has sorted now
        
        
        
        
        
    }
    
    
    
    
    public int add(int val) {
        // if the element to be added doesn't disturb order then return the kth element
        if(elementsAdded==k)
            if( nums[k-1] > val)
                return nums[k-1];
            
        
    
            
        
        
        
        // traverse from 0 to elementsAdded - 1 
        // traverse until the val added is lesser than cur
        int cur = 0;
        while(cur < elementsAdded && nums[cur] > val)
            cur++;
        
        
        
        // we have to insert the value at cur and right shift the right side's elements
        int insert = val;
        while(cur < k){
            // store the cur element which need to be shifted right
            int tmp = nums[cur];
            nums[cur] = insert;
            // for next itr the cur needed to be inserted
            insert = tmp;
            cur++;
        }
        
        
        if(elementsAdded < k)
            elementsAdded++;
        return nums[k-1];
        
        
        
        
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
