package solutions.그래프.촌수계산_2644.규연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_CalcKinship {

    static class Person {
        int num;
        boolean visited;
        int parent;
        ArrayList<Integer> children;

        public Person() {
            this.visited = false;
            this.num = -1;
            this.parent = -1;
            this.children = new ArrayList<>();
        }
    }

    static class Source {
        private BufferedReader br;
        private StringTokenizer stk;
        int n, resX, resY, m;
        ArrayList<Person> relation;

        public Source() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));

            n = Integer.parseInt(br.readLine()); // 전체 사람 수
            stk = new StringTokenizer(br.readLine(), " ");
            resX = Integer.parseInt(stk.nextToken()) - 1; // 촌수 비교할 사람 x
            resY = Integer.parseInt(stk.nextToken()) - 1; // 촌수 비교할 사람 y
            relation = new ArrayList<>(); // 관계를 담을 배열
            for (int i = 0; i < n; i++) {
                relation.add(new Person()); // 초기화 & 추가
            }

            m = Integer.parseInt(br.readLine()); // 관계의 개수
            for (int i = 0; i < m; i++) { // 관계의 계수 만큼 relation 배열에 정보 담기
                stk = new StringTokenizer(br.readLine(), " ");
                int parent = Integer.parseInt(stk.nextToken()) - 1;
                int child = Integer.parseInt(stk.nextToken()) - 1;
                relation.get(parent).children.add(child);
                relation.get(child).parent = parent;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Source source = new Source();
        int result = BFS(source.relation, source.resX, source.resY);
        System.out.println(result != -1 ? result + 1 : result);
    }

    private static int BFS(ArrayList<Person> relation, int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start); // 시작
        while (!queue.isEmpty()) {
            int num = queue.poll();
            Person person = relation.get(num);
            if (num == end) break; // 찾는 사람이면 리턴
            person.visited = true;
            for (Integer child : person.children) {
                if (!relation.get(child).visited) {
                    relation.get(child).num = person.num + 1;
                    queue.offer(child);
                }
            }
            if (person.parent != -1 && !relation.get(person.parent).visited) {
                relation.get(person.parent).num = person.num + 1;
                queue.offer(person.parent);
            }
        }
        return relation.get(end).num;
    }

}
