package com.netcracker.musiclibrary.view;

import com.netcracker.musiclibrary.controller.Controller;
import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import com.netcracker.musiclibrary.model.ModelChangeListener;

import java.time.Duration;
import java.util.Scanner;

public class View implements ModelChangeListener {

    private Model model;
    private Controller controller;

    public View(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        model.addChangeListener(this);
        onModelChangeListener();
    }

    public void onModelChangeListener(){
            System.out.printf("%-15s%n", "Жанр");
            for (Genre genre : model.getGenresCollection())
                System.out.printf("%-15s%n", genre.getName());
            System.out.println();
            System.out.printf("%-20s%-15s%-15s%-15s%-15s%n", "Название трека", "Длительность", "Альбом", "Певец", "Жанр");
            for (Track track : model.getTracksCollection())
                System.out.printf("%-20s%-15s%-15s%-15s%-15s%n", track.getName(), track.getRecordLength(), track.getAlbum(), track.getSinger(), track.getGenre());
            Scanner in = new Scanner(System.in);
            System.out.println();
            System.out.println("МЕНЮ");
            System.out.println("1 - Добавить трек");
            System.out.println("2 - Добавить жанр");
            System.out.print("Введите действие: ");
            switch (in.nextLine()) {
                case ("1"):
                    System.out.print("Введите название песни: ");
                    String nameTrack = in.nextLine();
                    System.out.print("Введите время записи (в секундах): ");
                    Duration time = Duration.ofSeconds(Integer.parseInt(in.nextLine()));
                    System.out.print("Введите альбом: ");
                    String album = in.nextLine();
                    System.out.print("Введите певца: ");
                    String singer = in.nextLine();
                    System.out.print("Введите жанр: ");
                    String genre = in.nextLine();
                    controller.createTrack(nameTrack, singer, album, time, genre);
                    break;
                case ("2"):
                    System.out.print("Введите название жанра: ");
                    String nameGenre = in.nextLine();
                    controller.createGenre(nameGenre, true);
                    break;
                default:
                    onModelChangeListener();
            }
    }
}
