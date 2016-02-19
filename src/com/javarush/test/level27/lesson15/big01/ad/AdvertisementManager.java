package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.*;

/**
 * Created by Олег Волков on 07.02.2016.
 */
public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    private  List<Advertisement> videos = new ArrayList<>();
    private long videosSum;
    private int videosTime;

    public void processVideos() {
//        sortStorage();
//
//        showStorage();

        videos = bestStorage(storage.list(), videos, timeSeconds);

        showBestStorage(videos);
    }

    private List<Advertisement> bestStorage(List<Advertisement> currentStorage, List<Advertisement> currentVideos, int currentTime) {
        List<Advertisement> newStorage = new ArrayList<>();
        for (Advertisement advertisement : currentStorage) {
            if (advertisement.getDuration() < currentTime) {
                newStorage.add(advertisement);
            }
        }

        if (newStorage.size() == 0) {
            // Check
            return currentVideos;
        } else {
            for (Advertisement advertisement : newStorage) {
                if (advertisement.getDuration() < currentTime) {
                    newStorage.add(advertisement);
                }
            }
            return currentVideos;
        }
    }

    private void showBestStorage(List<Advertisement> videos) {
        if (videos.size() == 0) {
            throw new NoVideoAvailableException();
        }
        for(Advertisement advertisement : videos) {
            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());
            advertisement.revalidate();
        }
    }

    private void sortStorage() {
        Collections.sort(storage.list(), new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int result = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
                if (result != 0)
                    return -result;

                long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

                return Long.compare(oneSecondCost1, oneSecondCost2);
            }
        });
    }

    private void showStorage() throws NoVideoAvailableException {
        int timeLeft = timeSeconds;
        for (Advertisement advertisement : storage.list()) {
            if (timeLeft < advertisement.getDuration()) {
                continue;
            }

            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());

            timeLeft -= advertisement.getDuration();
            advertisement.revalidate();
        }

        if (timeLeft == timeSeconds) {
            throw new NoVideoAvailableException();
        }
    }
}
