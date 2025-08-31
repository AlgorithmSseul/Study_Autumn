class Solution {  //88mb   20.59m
    int solution(int[][] land) {
        int answer = 0;
        
        int[][] ans =new int [land.length][4];
        ans [0][0] =land[0][0];
        ans [0][1] =land[0][1];
        ans [0][2] =land[0][2];
        ans [0][3] =land[0][3];
        
        for(int i=1;i<land.length;i++){  //각 행에서 이전 행의 최댓값을 더해서 저장 
            for(int j=0;j<4;j++){
                  int max=0;
                for(int k=0;k<4;k++){
                    if(j!=k){  //같은 열만 제외하고최댓값 계산
                        max = Math.max(max,land[i][j]+ans[i-1][k]);
                    }
                }
                    ans[i][j]=max;
                if(i==land.length-1){
                    answer=Math.max(answer,max);
                }
            }
        }
        return answer;
    }
}