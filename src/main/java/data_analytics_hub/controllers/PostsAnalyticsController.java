package data_analytics_hub.controllers;

import data_analytics_hub.Session;
import data_analytics_hub.modal.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PostsAnalyticsController {

    @FXML
    private Label lblPostsCount;

    @FXML
    private PieChart pieChart;


    @FXML
    void initialize(){
        initPostsCount();
        initPieChart();
    }

    private void initPostsCount(){
        lblPostsCount.setText(
                String.valueOf(Session.currentUser.getCollection().getPostsList().size())
        );
    }

    private void initPieChart(){
        pieChart.getData().clear();
        pieChart.setData(analysePosts());
    }

    private ObservableList<PieChart.Data> analysePosts(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int[] typesCount = new int[3];  //0: post's share < 100; 1: post's share >= 100 && < 1000; 2: post's share >= 1000
        ArrayList<Post> myPosts = Session.currentUser.getCollection().getPostsList();
        for(Post post : myPosts){
            if(post.getShares() < 100){
                typesCount[0]++;
            }else if(post.getShares() < 1000){
                typesCount[1]++;
            }else{
                typesCount[2]++;
            }
        }
        pieChartData.add(new PieChart.Data("Category 1", typesCount[0]));
        pieChartData.add(new PieChart.Data("Category 2", typesCount[1]));
        pieChartData.add(new PieChart.Data("Category 3", typesCount[2]));
        return pieChartData;
    }
}
