// 33. 搜索旋转排序数组
/*
  首先想到的是找到顶端，然后就变成有序数组的二分了
  想法很简单但是实现起来真是历尽千难万险
  先是找顶端，对于一个峰值在中间的区间来讲，很容易想到一种判断方式
  但是这种方式又有一种mid正好是峰值的情况需要break
  然后这种方式并不能适用于区间已经是有序区间的情况，所以需要在循环开始前在做一个有序的判断
  至此，终于能正确的找到顶点
  第二步同样曲折，有了峰值以后，可以认为划出一个有序数组了，但是这个有序数组的维护可太难了
  首先是越界问题，除了超出数组索引的情况需要处理之外，left和right的大小比较，mid的求解，都要重写
  其次，left和right还有一种特殊的越界判断，指，left一直右移，最后又回到原位的情况，不处理的话会无限循环
  处理方式就是，返回-1
  因为这种情况出现的原因是left已经遍历了一圈了，都没找到想要的值
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            if (nums[right] > nums[left]) {
                mid = right;
                break;
            }
            if (nums[mid] > nums[mid + 1]) {
                break;
            }
            if (nums[mid] > nums[right]) {
                left = mid + 1;
                mid = left + (right - left) / 2;
            } else {
                right = mid - 1;
                mid = left + (right - left) / 2;
            }
        }
        int top = mid;
        left = (top + 1) % nums.length;
        right = top;
        mid = getMid(left, right, nums.length);
        while (!bigger(left, right, top)) {
            if (nums[mid] > target) {
                right = (mid - 1 + nums.length) % nums.length;
                if (right == top) {
                    return -1;
                }
                mid = getMid(left, right, nums.length);
            } else if (nums[mid] == target) {
                return mid;
            } else {
                left = (mid + 1) % nums.length;
                if (left == (top + 1) % nums.length) {
                    return -1;
                }
                mid = getMid(left, right, nums.length);
            }
        }
        return -1;
    }

    public int getMid(int left, int right, int arrayLength) {
        if (left > right) {
            return (left + (right + arrayLength - left) / 2) % arrayLength;
        } else if (left == right) {
            return left;
        } else {
            return left + (right - left) / 2;
        }
    }

    public boolean bigger(int left, int right, int top) {
        if (left > top && right > top) {
            return left > right;
        }
        if (left <= top && right <= top) {
            return left > right;
        }
        return left < right;
    }
}
/*
  上一种解法写起来其实非常的折磨，在我被折磨的过程中，想到了这么一种优化
  既然你都找到顶点了，顶点左右本身就是一次二分，就不用费尽心思维护left和right了
  果然快了很多
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;
        while (left <= right) {
            if (nums[right] > nums[left]) {
                mid = right;
                break;
            }
            if (nums[mid] > nums[mid + 1]) {
                break;
            }
            if (nums[mid] > nums[right]) {
                left = mid + 1;
                mid = left + (right - left) / 2;
            } else {
                right = mid - 1;
                mid = left + (right - left) / 2;
            }
        }
        int top = mid;
        if (target >= nums[0]) {
            left = 0;
            right = top;
        } else {
            left = (top + 1) % nums.length;
            right = nums.length - 1;
        }
        mid = left + (right - left) / 2;
        while (left <= right) {
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            } else {
                left = mid + 1;
            }
            mid = left + (right - left) / 2;
        }
        return -1;
    }
}
/*
  在做完153题之后，我有一个问题，为什么33题就写的如此的轻松
  原因在于，二分的时候，left的变动尤为重要，尽量让left变
  所以这个题，寻找峰值就可以变为寻找谷值
  如果你硬要找峰值呢，也可以
  之前的做法就是硬找峰值，left和right双向奔赴，用了一个if拦住了left = mid + 1后会错过的可能
  但是我在实现的时候，借鉴了153题的经验，想直接比较nums[left]和nums[right]
  意外的发现，循环里的if也不用写了，这是为什么呢
  原因在于，判断的规则，正好就是，我们在面对有序序列时的操作规则
  回看153题，为什么那么别扭，因为那个逻辑在面对有序序列的时候，造成的效果正好相反
  所以，这里产生了第二个感悟：nums[mid]可以跟nums[left]比，也可以跟nums[right]比
  这可不是随便选的，需要让它在处理正常序列的时候，也能有一样的效果
  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
*/
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        int top = nums[right] > nums[left] ? right : left;
        left = (top + 1) % nums.length;
        right = top;
        mid = getMid(left, right, nums.length);
        while (!bigger(left, right, top)) {
            if (nums[mid] > target) {
                right = (mid - 1 + nums.length) % nums.length;
                if (right == top) {
                    return -1;
                }
                mid = getMid(left, right, nums.length);
            } else if (nums[mid] == target) {
                return mid;
            } else {
                left = (mid + 1) % nums.length;
                if (left == (top + 1) % nums.length) {
                    return -1;
                }
                mid = getMid(left, right, nums.length);
            }
        }
        return -1;
    }

    public int getMid(int left, int right, int arrayLength) {
        if (left > right) {
            return (left + (right + arrayLength - left) / 2) % arrayLength;
        } else if (left == right) {
            return left;
        } else {
            return left + (right - left) / 2;
        }
    }

    public boolean bigger(int left, int right, int top) {
        if (left > top && right > top) {
            return left > right;
        }
        if (left <= top && right <= top) {
            return left > right;
        }
        return left < right;
    }
}

/*
  其实我还瞟了一眼题解，发现没有必要找峰值
  直接在这个“半有序”的数组上就能做二分，我假装没看见得了，我的做法也还ok
*/
