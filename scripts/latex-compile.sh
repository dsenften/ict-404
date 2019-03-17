#!/usr/bin/env bash

xelatex -shell-escape exam1.tex
xelatex -shell-escape exam2.tex
xelatex -shell-escape exam3.tex

xelatex -shell-escape main.article.tex
xelatex -shell-escape main.beamer.tex
