class Solution {
    public int[] solution(int brown, int yellow) {
        for (int i = 1; i <= yellow; i++) {
            if (yellow % i == 0) {
                int h = i;
                int w = yellow / i;
                int b = ((w + 2) + h) * 2;
                
                if (b == brown) return new int[] {w + 2, h + 2};
            }
        }
        
        return null;
    }
}
