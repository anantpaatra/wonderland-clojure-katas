(ns alphabet-cipher.coder
  (:require [clojure.string :as s]
            [clojure.set :refer [map-invert]]))

(def columns {:a 0 :b 1 :c 2 :d 3 :e 4 :f 5 :g 6 :h 7 :i 8 :j 9 :k 10 :l 11
              :m 12 :n 13 :o 14 :p 15 :q 16 :r 17 :s 18 :t 19 :u 20 :v 21 :w 22
              :x 23 :y 24 :z 25})

(def substitution-chart
  {:a "abcdefghijklmnopqrstuvwxyz"
   :b "bcdefghijklmnopqrstuvwxyza"
   :c "cdefghijklmnopqrstuvwxyzab"
   :d "defghijklmnopqrstuvwxyzabc"
   :e "efghijklmnopqrstuvwxyzabcd"
   :f "fghijklmnopqrstuvwxyzabcde"
   :g "ghijklmnopqrstuvwxyzabcdef"
   :h "hijklmnopqrstuvwxyzabcdefg"
   :i "ijklmnopqrstuvwxyzabcdefgh"
   :j "jklmnopqrstuvwxyzabcdefghi"
   :k "klmnopqrstuvwxyzabcdefghij"
   :l "lmnopqrstuvwxyzabcdefghijk"
   :m "mnopqrstuvwxyzabcdefghijkl"
   :n "nopqrstuvwxyzabcdefghijklm"
   :o "opqrstuvwxyzabcdefghijklmn"
   :p "pqrstuvwxyzabcdefghijklmno"
   :q "qrstuvwxyzabcdefghijklmnop"
   :r "rstuvwxyzabcdefghijklmnopq"
   :s "stuvwxyzabcdefghijklmnopqr"
   :t "tuvwxyzabcdefghijklmnopqrs"
   :u "uvwxyzabcdefghijklmnopqrst"
   :v "vwxyzabcdefghijklmnopqrstu"
   :w "wxyzabcdefghijklmnopqrstuv"
   :x "xyzabcdefghijklmnopqrstuvw"
   :y "yzabcdefghijklmnopqrstuvwx"
   :z "zabcdefghijklmnopqrstuvwxy"})

(defn char-to-key [c]
  (keyword (str c)))

(defn expand-keyword [key desired-length]
  (let [key-length (count key)]
    (cond (> key-length desired-length)
          (subs key 0 desired-length)
          (= key-length desired-length)
          key
          (< key-length desired-length)
          (->> (cycle key)
               (take desired-length)
               (apply str)))))

(defn get-encoded-letter [letter-x rows letter-y columns]
  (get ((char-to-key letter-x) rows) ((char-to-key letter-y) columns)))

(defn encode [keyword message]
  (let [expanded-keyword (expand-keyword keyword (count message))]
    (loop [[char-of-key & rest-of-key] expanded-keyword
           [char-of-message & rest-of-message] message
           encoded-message ""]
      (if (char? char-of-key)
        (recur rest-of-key rest-of-message
               (->> (get-encoded-letter char-of-key substitution-chart
                                        char-of-message columns)
                    (str encoded-message)))
        encoded-message))))

(defn get-decoded-letter [c c2]
  (let [position (columns (char-to-key c))]
    (->> (filter #(= c2 (nth (second %) position)) substitution-chart)
         (into {})
         (apply #(second (s/join %))))))

(defn decode [keyword message]
  (let [expanded-keyword (expand-keyword keyword (count message))]
    (loop [[char-of-key & rest-of-key] expanded-keyword
           [char-of-message & rest-of-message] message
           encoded-message ""]
      (if (char? char-of-key)
        (recur rest-of-key rest-of-message
               (->> (get-decoded-letter char-of-key char-of-message)
                    (str encoded-message)))
        encoded-message))))

(defn decipher [cipher message]
  "decypherme")
