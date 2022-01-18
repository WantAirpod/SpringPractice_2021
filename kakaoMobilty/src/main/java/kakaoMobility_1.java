
import java.util.*;

public class kakaoMobility_1 {
    /*class Solution {
        public String solution(S    tring S, String C) {
            ArrayList<String> answer = new ArrayList<>();
            HashMap<String, Integer> nameMap = new HashMap<String, Integer>();
            String[] nameList = S.split(", ");
            Integer index = 0;
            for(String name : nameList) {
                String nameTemp = name.replace("-", " ");
                String[] nameListTemp = nameTemp.split(" ");
                String emailName = "";
                Character firstName = nameListTemp[0].toLowerCase().charAt(0);
                Character middleName;
                String lastName;

                emailName += firstName;
                if(nameListTemp.length == 2) {
                    lastName = nameListTemp[1].toLowerCase();
                    if(lastName.length() > 7) {

                    }
                    emailName += lastName;
                } else {
                    middleName = nameListTemp[1].toLowerCase().charAt(0);
                    lastName = nameListTemp[2].toLowerCase();
                    emailName += middleName;
                    emailName += lastName;
                }

                String resultName = "";
                String companyName = C.toLowerCase() + ".com";
                if(nameMap.containsKey(emailName)) {
                    Integer sameNameCount = nameMap.get(emailName);
                    resultName = emailName + sameNameCount.toString();
                    nameMap.put(emailName, sameNameCount + 1);
                } else {
                    nameMap.put(emailName, 0);
                    resultName = emailName + companyName;
                }

                String resultEmail = "";
                if(index == 0) {
                    resultEmail = name + " <" + resultName + ">";
                } else {
                    resultEmail = ", " + name + " <" + resultName + ">";
                }
                answer.add(resultEmail);
                index++;
            }

            String result = "";
            for(String resultTemp : answer) {
                result += resultTemp;
            }

            return result;
        }
    }*/

        class Solution {
            public int solution(int[] T, int[] A) {
                Integer answer = 0;
                Boolean[] visitArr = new Boolean[100001];
                Arrays.fill(visitArr, false);
                HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

                Integer index = 0;
                for(Integer num : T) {
                    map.put(index, num);
                    index++;
                }

                for(Integer num : A) {
                    Integer upperNum = num;
                    if(!visitArr[upperNum]) {
                        visitArr[upperNum] = true;
                        answer++;
                    }
                    while(true) {
                        Integer upperNumTemp = map.get(upperNum);
                        if(!visitArr[upperNumTemp]) {
                            visitArr[upperNumTemp] = true;
                            answer++;
                        }

                        upperNum = upperNumTemp;

                        if(upperNumTemp == 0) {
                            break;
                        }
                    }
                }

                return answer;
            }
        }

        public static void main(String[] args) {

        }

    }

