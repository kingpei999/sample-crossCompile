//
// Created by kingpei on 18-8-23.
//
#include <stdio.h>
#include <lame/lame.h>
#ifndef LAMEPROJECT_MP3ENCODERWORKER_H
#define LAMEPROJECT_MP3ENCODERWORKER_H
class Mp3Encoder{
private:
    FILE* pcmFile;
    FILE* mp3File;
    lame_t lameClient;

public:
    Mp3Encoder();
    ~Mp3Encoder();
    int Init(const char* pcmFilePath, const char* mp3FilePath, int sampleRate, int channels, int bitRate);
    void Encode();
    void Destroy();
};
#endif //LAMEPROJECT_MP3ENCODERWORKER_H
