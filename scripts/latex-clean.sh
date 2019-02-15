#!/usr/bin/env bash

# Remove temporary TeX files
rm -f *.aux
rm -f *.loe
rm -f *.lof
rm -f *.log
rm -f *.lol
rm -f *.lot
rm -f *.lox
rm -f *.nav
rm -f *.out
rm -f *.pyg
rm -f *.snm
rm -f *.toc
rm -f *.vrb

rm -rf _minted*

# Generated BlueJ files
find .. -name "*.class" -exec rm "{}" \;
find .. -name "*.ctxt" -exec rm "{}" \;

# Generated .pdf files
rm -f exam.pdf
rm -f main.article.pdf
rm -f main.beamer.pdf
