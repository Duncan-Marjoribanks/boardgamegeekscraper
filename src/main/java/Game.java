public class Game {

    private int rank;
    private String imageUrl;
    private String name;

    public Game(int rank, String imageUrl, String name){
        this.rank = rank;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name:  ");
        sb.append(name);
        sb.append("\n");
        sb.append("Rank:  ");
        sb.append(Integer.toString(rank));
        sb.append("\n");
        sb.append("Image: ");
        sb.append(imageUrl);
        sb.append("\n");
        return sb.toString();
    }
}
