package com.cloud.jack.app.test;

import java.util.*;

public class VollyBallTest {


    public static void main(String[] args) {
        List<String> peopleList = new ArrayList<>(Arrays.asList("捷克梗","狼青","郊狼","龙睛","绿翅鸭","灰林鸽"));
        List<List<List<String>>> battleList = getBattleList(peopleList, 5);
        List<String> battle = new ArrayList<>();
        for (int i = 0; i < battleList.size(); i++) {
            battle.add(String.format("第%s场： %s VS %s",i+1,String.join("/",battleList.get(i).get(0)),String.join("/",battleList.get(i).get(1))));
        }
        battle.forEach(System.out::println);
    }

    /**
     * 生成对战表
     * @param peopleList 参赛人列表
     * @param roundNum 每人场次 默认 人数-1
     * @return 对战信息
     */
    public static <T> List<List<List<T>>> getBattleList(List<T> peopleList, int roundNum) {
        while ((peopleList.size() * roundNum) % 4 != 0) {
            roundNum++;
        }
        //生成搭档池子
        List<List<T>> matchListPoor = getPartnerList(peopleList, roundNum);
        //总场次
        int allRound = matchListPoor.size() / 2;
        List<List<List<T>>> res = new ArrayList<>();
        List<List<T>> rivalry = new ArrayList<>();
        int round = 0;
        while (matchListPoor.size() > 0) {
            List<T> per = matchListPoor.get(0);
            matchListPoor.remove(0);
            if (rivalry.size() == 0) {
                rivalry.add(per);
                round = 1;
            } else if (!rivalry.get(0).contains(per.get(0)) && !rivalry.get(0).contains(per.get(1))) {
                rivalry.add(per);
            } else {
                matchListPoor.add(per);
                round = 2;
            }
            if (rivalry.size() == 2) {
                res.add(Arrays.asList(rivalry.get(0), rivalry.get(1)));
                rivalry.clear();
            }
            if (matchListPoor.size() == 1 && round == 2) {
                matchListPoor.addAll(rivalry);
                for (int j = 0; j < res.size(); j++) {
                    if (!res.get(j).get(0).contains(matchListPoor.get(0).get(0)) && !res.get(j).get(0).contains(matchListPoor.get(0).get(0))) {
                        List<T> temp = res.get(j).get(1);
                        res.get(j).set(1, matchListPoor.get(0));
                        matchListPoor.set(0, temp);
                        if (!matchListPoor.get(0).contains(matchListPoor.get(1).get(0)) && !matchListPoor.get(0).contains(matchListPoor.get(1).get(1))) {
                            res.add(Arrays.asList(matchListPoor.get(0), matchListPoor.get(1)));
                            break;
                        }
                    }
                }
            }
            if (res.size() == allRound) {
                break;
            }
        }
        return sort(res);
    }


    /**
     * 排序(避免有人连打多场)
     * @param oldMatchList 初始对战表
     * @return 排序后对战表
     */
    private static <T> List<List<List<T>>> sort(List<List<List<T>>> oldMatchList) {
        //排序后新对战表
        List<List<List<T>>> newMatchList = new ArrayList<>();
        newMatchList.add(oldMatchList.get(0));
        oldMatchList.remove(0);

        while (oldMatchList.size() >0){
            //新对战表最后一组
            List<List<T>> lastList = newMatchList.get(newMatchList.size() - 1);
            //最后一组的人
            List<T> lastMember = new ArrayList<>();
            for (List<T> list : lastList) {
                lastMember.addAll(list);
            }
            for (int i = oldMatchList.size()-1; i >=0; i--) {
                //旧对战表中每一局的人
                List<T> member = new ArrayList<>();
                for (List<T> list : oldMatchList.get(i)) {
                    member.addAll(list);
                }
                if (!member.contains(lastMember.get(0)) && !member.contains(lastMember.get(1)) && !member.contains(lastMember.get(2)) && !member.contains(lastMember.get(3))){
                    newMatchList.add(oldMatchList.get(i));
                    oldMatchList.remove(i);
                    break;
                }
                if (i == 0){
                    newMatchList.add(oldMatchList.get(i));
                    oldMatchList.remove(i);
                    break;
                }
            }
        }
        return newMatchList;
    }



    /**
     * 生成搭档列表
     * @param peopleList 参赛人列表
     * @param roundNum 每人场次 默认 人数-1
     * @return 所有搭档列表
     */
    public static<T> List<List<T>> getPartnerList(List<T> peopleList, int roundNum) {
        //总人数
        int totalNum = peopleList.size();
        // 所有有可能搭档的组
        List<List<T>> partnerGroupList = new ArrayList<>();
        // 出场的搭档数组
        List<List<T>> partnerGroupListReal = new ArrayList<>();
        // 记录每人上场的次数对象
        Map<T, Integer> pShouldRoundNum = new HashMap<>();
        int groundNum = 0;
        for (int i = 0; i < peopleList.size(); i++) {
            // 预先定下前面的搭档池数
            pShouldRoundNum.put(peopleList.get(i), peopleList.size() - 1);
        }
        // 整除
        if ((totalNum * roundNum) % 4 == 0) {
            groundNum = (int) Math.floor((double) (peopleList.size() * roundNum) / 2);
            for (int i = 0; i < peopleList.size(); i++) {
                T e = peopleList.get(i);
                for (int j = i + 1; j < peopleList.size(); j++) {
                    T e1 = peopleList.get(j);
                    List<T> partnerGroup = new ArrayList<>();
                    partnerGroup.add(e);
                    partnerGroup.add(e1);
                    // 将搭档添加到搭档组列表中
                    partnerGroupList.add(partnerGroup);
                }
            }
            // 将所有搭档组添加到实际搭档列表中
            partnerGroupListReal.addAll(partnerGroupList);
            // 必须每人多打几场
            if (roundNum > peopleList.size() - 1) {
                // 创建临时搭档组列表，用于随机选择搭档组进行调整
                List<List<T>> tempPartnerGroupList = new ArrayList<>(partnerGroupList);
                boolean flag = true;
                Random rand = new Random();
                while (flag) {
                    // 从临时搭档组列表中随机选择一个搭档组的索引
                    int index = rand.nextInt(tempPartnerGroupList.size());
                    // 从临时搭档组列表中移除选中的搭档组
                    List<T> partnerGroupListRealOne = tempPartnerGroupList.remove(index);
                    // 获取第一个选手
                    T p1 = partnerGroupListRealOne.get(0);
                    // 获取第二个选手
                    T p2 = partnerGroupListRealOne.get(1);
                    // 如果两个选手还未达到指定场次，则将该搭档组加入实际搭档列表中，并更新他们的场次计数器
                    if (pShouldRoundNum.get(p1) < roundNum && pShouldRoundNum.get(p2) < roundNum) {
                        partnerGroupListReal.add(partnerGroupListRealOne);
                        pShouldRoundNum.put(p1, pShouldRoundNum.get(p1) + 1);
                        pShouldRoundNum.put(p2, pShouldRoundNum.get(p2) + 1);
                    }
                    if (partnerGroupListReal.size() == groundNum) {
                        flag = false;
                    }
                }
            }
            return partnerGroupListReal;
        } else {
            return new ArrayList<>();
        }
    }


}
