package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        ArrayList<RacingCar> cars = new ArrayList<>();
        ArrayList<RacingCar> winners = new ArrayList<>();

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();
        String[] names = input.split(",");

        for (String name : names) {
            try{
                RacingCar RC = new RacingCar(name);
                cars.add(RC);
            } catch (IllegalArgumentException e){
                System.out.println("오류 발생 : " + e.getMessage());
                System.out.println("\"" +name +"\"" + "은 사용하실 수 없습니다.");
                System.exit(0);
            }
        }

        System.out.println("시도할 회수는 몇회인가요?");
        int attempt = Integer.parseInt(Console.readLine());
        System.out.println("실행 결과\n");

        for (int i = 0; i < attempt; i++) {
            for (RacingCar car : cars) {
                car.updateScoreOnMove();
                System.out.print(car.name + " : ");
                car.printScore();
            }
            System.out.println();
        }
        int maxScore = 0;
        for (RacingCar car : cars) {
            if (car.getScore() > maxScore) {
                maxScore = car.getScore();
            }
        }

        for (RacingCar car : cars) {
            if (car.getScore() == maxScore) {
                winners.add(car);
            }
        }

        System.out.print("최종 우승자 :");
        for (RacingCar car : winners) {
            System.out.print(car.getName() + " ");
        }
    }

    private static class RacingCar {
        private final String name;
        private int score = 0;

        public RacingCar(String name) {
            checkValidLength(name);
            this.name = name;
        }

        public void checkValidLength(String name) {
            if (name.length() > 5) { // 이름이 5자 초과일 때
                throw new IllegalArgumentException("이름은 5자 이하만 가능합니다.");
            }
        }

        public void updateScoreOnMove() {
            if(Randoms.pickNumberInRange(0,9)>=4) {
                score++;
            }
        }

        public void printScore() {
            for (int i = 0; i < score; i++){
                System.out.print("-");
            }
            System.out.println();
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}
