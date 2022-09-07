import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements  ICRUD{

    ArrayList<Word> list;
    Scanner s;
    /*
    => 난이도(1,2,3) & 새 단어 입력 : 1 driveway
    뜻 입력 : 차고 진입로
    새 단어가 단어장에 추가되었습니다.
    */

    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();  //공백 포함
        return new Word(0, level, word, meaning);
    }

    public void addItem(){
        Word one = (Word)add();
        list.add(one);

        System.out.println();
        System.out.println("새 단어가 단어장에 추가되었습니다. ");
        System.out.println();
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }
    /*
    => 원하는 메뉴는? 1
    ------------------------------
    1 ***       superintendent  관리자, 감독관
    2 *                eletric  전기의, 전기를 생산하는
    3 **             equipment  장비, 용품
    4 *                   pole  기둥, 장대
    ------------------------------

    */
    public void listAll(){
        System.out.println();
        System.out.println("------------------------------");
        for(int i=0;i<list.size();i++){
            System.out.print((i+1) + " ");
            //toString()으로 해놨기 때문에 바로 이렇게
            System.out.println(list.get(i).toString());
        }
        System.out.println("------------------------------");
        System.out.println();
    }

    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idlist = new ArrayList<>();
        int j=0;

        System.out.println();
        System.out.println("------------------------------");
        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();    //리스트에 있는 영단어 하나 가져옴
            if(! word.contains(keyword)) continue;
            System.out.print((j + 1) + " ");
            //toString()으로 해놨기 때문에 바로 이렇게
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("------------------------------");
        return idlist;
    }

    public void updateItem(){
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택 : ");
        int id = s.nextInt();   //id 뒤에 enter을 없애주기 위해 다음줄을 작성
        s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id-1)); //객체 꺼내옴
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다. ");
    }

    public void deleteItem(){
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();   //id 뒤에 enter을 없애주기 위해 다음줄을 작성
        s.nextLine();
        System.out.print("=> 정말로 삭제하실래요?(Y/N) ");
        String ans = s.next();
        if(ans.equalsIgnoreCase("y")){
            list.remove((int)idlist.get(id-1));
            System.out.println("단어가 삭제되었습니다. ");
        } else System.out.println("취소되었습니다. ");
    }

}
