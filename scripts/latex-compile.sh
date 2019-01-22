#!/usr/bin/env bash

xelatex -shell-escape main.article.tex
xelatex -shell-escape main.beamer.tex

open -a Skim main.article.pdf main.beamer.pdf
