/*

tfe_od_layout_bottom_sheet.xml

            <ImageView
                android:id="@+id/minus"                     // minus button
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@drawable/ic_baseline_remove" />

            <TextView
                android:id="@+id/threads"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="10dp"
                android:layout_marginRight="10dp"
                android:text="1"
                android:textColor="@android:color/black"
                android:textSize="14sp" />

            <ImageView
                android:id="@+id/plus"                       // plus button
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@drawable/ic_baseline_add" />

*/


// public class ImageView extends View


@Override
  public void onClick(View v) {
    
    // Update thread count (increment) on click (of R.id.plus)
    if (v.getId() == R.id.plus) {
    
      String threads = threadsTextView.getText().toString().trim();  // TV value in string format
      int numThreads = Integer.parseInt(threads);                    // threads count
 
      // Max Thread count allowed is 8
      if (numThreads >= 9) return;
      numThreads++;
      // Update TV with the updated (just incremented) value
      threadsTextView.setText(String.valueOf(numThreads));
      // Update thread count (increment)
      setNumThreads(numThreads);
      
     
    // Update thread count (increment) on click (of R.id.plus)
    } else if (v.getId() == R.id.minus) {
      
      String threads = threadsTextView.getText().toString().trim();
      int numThreads = Integer.parseInt(threads);
      
      if (numThreads == 1) return;
      numThreads--;
      // Update TV with the updated (just decremented) value
      threadsTextView.setText(String.valueOf(numThreads)); 
      // Update thread count (decrement)
      setNumThreads(numThreads);

    }
  }
